package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.Event;
import com.facebooktrumps.client.EventListener;
import com.facebooktrumps.client.Model;
import com.facebooktrumps.client.UpdateViewEvent;
import com.facebooktrumps.client._root.RootControllerEvent;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.entities.Turn;
import com.google.gwt.user.client.Random;

/**
 * Model class for the game, controls all the child triads.
 * @author Jim
 *
 */
public class PlayGameModel extends Model {
	
	private TrumpSession	 	_session;	

	public PlayGameModel(TrumpSession session) {
		_session = session;
	}
	
	public void doInit() {
		addPlayGameEventListener();
	}

	// Handle 
	private void addPlayGameEventListener() {
		_controller.register(new PlayGameEvent(), new EventListener(){
			public void handleEvent(Event event) {
				// Take appropriate action:
				switch (((Integer) event.getPayload()).intValue()) {
		 			case PlayGameEvent.PLAYERMOVE:
		 				playerMove();
		 				break;
		 			case PlayGameEvent.NEXTTURN:
		 				nextTurn();
		 				break;
		 			case PlayGameEvent.OPPONENTMOVE:
		 				opponentMove();
		 				break;
				}
			}
		});		
		
	}

	protected void opponentMove() {
		_session.getCurrentGame().setPlayed(true);
		determineBestMove();
		_session.getCurrentGame().setWon(evaluateWinner(_session.getCurrentGame().getMove()));
		if (_session.getCurrentGame().hasWon() == 0) {
			_session.getCurrentGame().incrementOpponentScore();
		} else if (_session.getCurrentGame().hasWon() == 1) {
			_session.getCurrentGame().incrementPlayerScore();
		}
	}

	private void determineBestMove() {
		// select random value.
		int r = Random.nextInt(3) +1;
		_session.getCurrentGame().setMove(r);
		
	}

	protected void nextTurn() {
		boolean playerPlaying = _session.getCurrentGame().isUserPlaying();
		
		_session.getCurrentGame().nextTurn();
		_session.getCurrentGame().setPlayed(false);
		
		_session.getCurrentGame().setUserPlaying(!playerPlaying);
		
		_session.getPlayerStack().getNextTrump();
		_session.getOpponentStack().getNextTrump();
	}

	protected void playerMove() {
		_session.getCurrentGame().setPlayed(true);
		_session.getCurrentGame().setWon(evaluateWinner(_session.getCurrentGame().getMove()));
		if (_session.getCurrentGame().hasWon() == 0) {
			_session.getCurrentGame().incrementOpponentScore();
		} else if (_session.getCurrentGame().hasWon() == 1) {
			_session.getCurrentGame().incrementPlayerScore();
		}
		
	}
	
	public int evaluateWinner(int move) {
		int plStat = 0; int opStat = 0;
		switch (move) {
			case Turn.INTERESTS: 	plStat = _session.getPlayerStack().getThisTrump().getInterests().intValue();
															opStat = _session.getOpponentStack().getThisTrump().getInterests().intValue();
															break;
			case Turn.EDUCATION: 	plStat = _session.getPlayerStack().getThisTrump().getEducation().intValue();
															opStat = _session.getOpponentStack().getThisTrump().getEducation().intValue();
															break;
			case Turn.WALLCOUNT: 	plStat = _session.getPlayerStack().getThisTrump().getWallcount().intValue();
															opStat = _session.getOpponentStack().getThisTrump().getWallcount().intValue();
															break;
			case Turn.AGE: 					plStat = _session.getPlayerStack().getThisTrump().getAge().intValue();
															opStat = _session.getOpponentStack().getThisTrump().getAge().intValue();
															break;
		}
		if (plStat < opStat) {
			// lose
			return 0;
		} else if (plStat > opStat) {
			// win
			return 1;
		} else {
			return 2;
		}
	}

}
