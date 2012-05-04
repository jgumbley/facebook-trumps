package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.TrumpCardWidget;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.Timer;

public class ShowResultsView extends AbstractGameView implements View {

	public ShowResultsView(TrumpSession session) {
			super(); 	_session = session; setMain(this);		
		}
	
	public void doInit() {
		TrumpCardWidget playersTrump = new TrumpCardWidget();
		TrumpCardWidget opponentsTrump = new TrumpCardWidget();
		
		playersTrump.populateCard(_session.getPlayerStack().getThisTrump());
		opponentsTrump.populateCard(_session.getOpponentStack().getThisTrump());
		
		playersTrump.highlightField(_session.getCurrentGame().getMove());
		opponentsTrump.highlightField(_session.getCurrentGame().getMove());
		
		timer = new Timer() {
		     public void run() {
		    	  fireEvent(new PlayGameEvent(PlayGameEvent.NEXTTURN));
		      }
		    };

		    // Schedule the timer to run once in 5 seconds.
		    timer.schedule(3500);
		 
		gamePanel.setWidget(0, 0, playersTrump);
		gamePanel.setWidget(0, 1, opponentsTrump);
	}

	
	

}
