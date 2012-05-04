package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.ConcealedTrump;
import com.facebooktrumps.client.widgets.TrumpCardWidget;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.Timer;

public class OpponentTurnView extends  AbstractGameView implements View {
	
	public OpponentTurnView(TrumpSession session) {
		super(); 	_session = session; setMain(this);		
	}
	
	public void doInit() {
			TrumpCardWidget playersTrump = new TrumpCardWidget();
			playersTrump.populateCard(_session.getPlayerStack().getThisTrump());
			setPlayerTrump(playersTrump);
			setOpponentTrump(new ConcealedTrump());
			timer = new Timer() {
			     public void run() {
			    	  fireEvent(new PlayGameEvent(PlayGameEvent.OPPONENTMOVE));
			      }
			    };	
			 timer.schedule(2000);
	}

}
