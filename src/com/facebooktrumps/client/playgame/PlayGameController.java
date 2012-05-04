package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.Controller;
import com.facebooktrumps.client.Event;
import com.facebooktrumps.client.EventListener;
import com.facebooktrumps.client.Model;
import com.facebooktrumps.client.Triad;
import com.facebooktrumps.client.UpdateViewEvent;
import com.facebooktrumps.client._root.RootController;
import com.facebooktrumps.client._root.RootControllerEvent;
import com.facebooktrumps.client.creategame.CreateGameController;
import com.facebooktrumps.client.creategame.CreateGameModel;
import com.facebooktrumps.client.creategame.CreateGameView;
import com.facebooktrumps.client.endgame.EndGameController;
import com.facebooktrumps.client.endgame.EndGameView;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.Loading;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class PlayGameController extends Controller {
	
	private TrumpSession	 	_session;
	
	private Triad 			 			viewTriad;

	public PlayGameController(TrumpSession session) {
		_session = session;
	}
	
	private PlayGameView getHeaderView() {
		return (PlayGameView) getView();
	}
	
	public void doInit(){
		initialiseHeaderView();
		registerUpdateViewEventListener();
		updateView();
	}
	
	private void initialiseHeaderView() {
		getHeaderView().setPlayer(_session.getPlayer());
		getHeaderView().setOpponent(_session.getOpponent());
	}
	
	protected void updateView() {
		if (!_session.getCurrentGame().isPlayed()) {
			if (_session.getCurrentGame().isUserPlaying()) {
				initialisePlayerTurnTriad();
			} else {
				initialiseOpponentTurnTriad();	
			}
		} else {
			if (_session.getCurrentGame().getPlayerScore() >= 10 || _session.getCurrentGame().getOpponentScore() >= 10) {
				fireEvent(new RootControllerEvent(RootControllerEvent.ENDGAME));
			} else {
				initialiseShowResultsTriad();
			}
			
		}
	}

	/**
	 * Initialise Player Turn HMVC Triad
	 */
	private void initialisePlayerTurnTriad() {			
		cleanUp();
		getHeaderView().playerTurn();
		viewTriad	 = 	new Triad(		
									PlayGameController.this, 							// Parent, this.
									new Model(), 												// Null Model.
									new PlayerTurnView(_session),   			// View.
									new PlayerTurnController());						// Controller.
		
	}
	
	private void initialiseOpponentTurnTriad() {
		cleanUp();
		getHeaderView().opponentTurn();
		viewTriad	 = 	new Triad(		
									PlayGameController.this, 							// Parent, this.
									new Model(), 												// Null Model.
									new OpponentTurnView(_session),   	// View.
									new PlayerTurnController());						// Controller.
	}
	
	private void initialiseShowResultsTriad() {
		cleanUp();
		viewTriad	 = new Triad(		
									PlayGameController.this, 							// Parent, this.
									new Model(), 												// Null Model.
									new ShowResultsView(_session),   		// View.
									new PlayerTurnController());						// Controller.
		int won = _session.getCurrentGame().hasWon();
		PlayGameView p = (PlayGameView) getView();
		p.setPlayer(_session.getPlayer());
		if (won == 1) {
			// sound.playSound("success03");
			p.showWon();
		} else if (won == 0) {
			// sound.playSound("uhoh");
			p.showLose();
		} else if (won == 2) {
			p.showDraw();
		}
	}

	private void cleanUp() {
		if (viewTriad != null) viewTriad.destroy();
	}
	
	private void registerUpdateViewEventListener() {
		register(new PlayGameEvent(), new EventListener(){
			public void handleEvent(Event event) {
				// Take appropriate action:
				updateView();
			}
		});		
	}

}			