package com.facebooktrumps.client.creategame;

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

/**
 * Welcome and display outcome of game initialisation. 
 */
public class CreateGameView extends EventCapableComposite implements View {
	
	private TrumpSession	 	_session;
	
	protected final 	FlexTable 	mainTable 		= new FlexTable();
	private 					Timer			timer ;

	public CreateGameView(TrumpSession session) {
		_session = session;
	}

	public void doInit() {
			setHeader(new HeaderPaneView());
			setMain(mainTable);
			addWelcome();
			addPlayer();
			addNumberOfTrumps();
			addOpponent();
	}
	
	private void addWelcome() {
		addElement("Welcome to Facebook Trumps!", new HTML("&nbsp;"));
	}

	private void addPlayer() {
		addElement("Player:", new PlayerPane(_session.getPlayer()));
	}

	private void addNumberOfTrumps() {
		addElement("Number of Trump Cards:", new HTML("" + (_session.getPlayerStack().size() + _session.getOpponentStack().size())));
		Button startButton = new Button("Browse Trumps");
		startButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {     
				
		        fireEvent(new RootControllerEvent(RootControllerEvent.TRUMPBROWSER));
		     }
        });
		addElement("&nbsp;",startButton );
	}

	private void addOpponent() {
		addElement("Opponent for this game:", new PlayerPane(_session.getOpponent()));
		timer = new Timer() {
		      public void run() {
		    	  addShuffled();
		      }
		    };
		 timer.schedule(1000);
	}

	private void addShuffled() {
		addElement("Shuffling Cards!", new Image("img/tick.gif"));
		timer = new Timer() {
		      public void run() {
		  		addDealt();
		      }
		    };
		 timer.schedule(1000);
	}
	
	private void addDealt() {
		addElement("Dealing Pack!", new Image("img/tick.gif"));
		timer = new Timer() {
		      public void run() {
		  		addToss();
				addContinueButton();
		      }
		    };
		 timer.schedule(1000);
	}

	private void addToss() {
		HTML result;
		if (_session.getCurrentGame().isUserPlaying()) {
			result = new HTML("You start!");
		} else {
			result = new HTML("Opponent starts");
		}
		addElement("Tossed coin!", result);
	}

	private void addContinueButton() {
		Button startButton = new Button("Start Game!");
		startButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {     
		        fireEvent(new RootControllerEvent(RootControllerEvent.PLAYGAME));
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
