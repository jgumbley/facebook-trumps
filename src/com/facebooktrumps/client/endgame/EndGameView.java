package com.facebooktrumps.client.endgame;

import com.facebooktrumps.client._root.RootControllerEvent;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.FacebookStyleLink;
import com.facebooktrumps.client.widgets.HeaderPaneView;
import com.facebooktrumps.client.widgets.PlayerPane;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class EndGameView extends EventCapableComposite implements View {
	
	private TrumpSession	 	_session;
	
	protected final 	FlexTable 	mainTable 		= new FlexTable();
	private 					Timer			timer ;

	public EndGameView(TrumpSession session) {
		_session = session;
	}

	public void doInit() {
	    
	    		/* should be in the model!!! */
        		_session.getPlayerStack().addAll(_session.getOpponentStack());
        		_session.setOpponentStack(null);
        		
        		// Proper shit:	    
			setHeader(new HeaderPaneView());
			setMain(mainTable);
			addWelcome();
			showWinner();
			addContinueButton();
	}
	
	private void addWelcome() {
		addElement("Game Over!", new HTML("&nbsp;"));
	}

	private void showWinner() {
	    if (_session.getCurrentGame().getPlayerScore() >= 10) {
		addElement("Winner:", new PlayerPane(_session.getPlayer()));
		addElement("Loser:", new PlayerPane(_session.getOpponent()));
		
	    } else {
		addElement("Winner:", new PlayerPane(_session.getOpponent()));
		addElement("Loser:", new PlayerPane(_session.getPlayer()));
	    }
	}

	private void addContinueButton() {
		Button startButton = new Button("Play Again!");
		startButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {     
		        fireEvent(new RootControllerEvent(RootControllerEvent.CREATEGAME));
		     }
        });
		addElement("&nbsp;",startButton );
	}

	private void addElement(String message, Widget content) {
		int row = mainTable.getRowCount() + 1;
		mainTable.setWidget(row, 0, new  FacebookStyleLink(message)); 
		mainTable.setWidget(row, 1, content); 
		mainTable.getCellFormatter().setAlignment(row, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
	}


}
