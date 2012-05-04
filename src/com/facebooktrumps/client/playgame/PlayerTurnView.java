package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.entities.Turn;
import com.facebooktrumps.client.widgets.ConcealedTrump;
import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.Sound;
import com.facebooktrumps.client.widgets.TrumpCardWidget;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class PlayerTurnView extends  AbstractGameView implements View {

		public PlayerTurnView(TrumpSession session) {
		super(); 	_session = session; setMain(this);		
	}
	
	public void doInit() {
	TrumpCardWidget playersTrump = new TrumpCardWidget();
	// Setting the trump card
	
	playersTrump.interests.addClickResponse(new ClickListener() {
		public void onClick(Widget sender) {
			_session.getCurrentGame().setPlayed(true);
			_session.getCurrentGame().setMove(Turn.INTERESTS);
			fireEvent(new PlayGameEvent(PlayGameEvent.PLAYERMOVE));
		}
	});
	
	playersTrump.age.addClickResponse(new ClickListener() {
		public void onClick(Widget sender) {
			_session.getCurrentGame().setPlayed(true);
			_session.getCurrentGame().setMove(Turn.AGE);
			fireEvent(new PlayGameEvent(PlayGameEvent.PLAYERMOVE));
		}
	});
	
	playersTrump.wallcount.addClickResponse(new ClickListener() {
		public void onClick(Widget sender) {
			_session.getCurrentGame().setPlayed(true);
			_session.getCurrentGame().setMove(Turn.WALLCOUNT);
			fireEvent(new PlayGameEvent(PlayGameEvent.PLAYERMOVE));
		}
	});
	
	playersTrump.education.addClickResponse(new ClickListener() {
		public void onClick(Widget sender) {
			_session.getCurrentGame().setPlayed(true);
			_session.getCurrentGame().setMove(Turn.EDUCATION);
			fireEvent(new PlayGameEvent(PlayGameEvent.PLAYERMOVE));
		}
	});
	
	playersTrump.populateCard(_session.getPlayerStack().getThisTrump());
	
	setPlayerTrump(playersTrump);
	setOpponentTrump(new ConcealedTrump());
	}
	
	

}