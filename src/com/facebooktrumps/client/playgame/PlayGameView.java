package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.entities.Player;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.Effector;
import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.Sound;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;

public class PlayGameView extends EventCapableComposite implements View {
	
	private final 	FlexTable 			headerPanel 	= new FlexTable();
	private 	 	 	Image 				pictureOfPlayer;
	private  	  		StatPanel			playerStats;
	private 	  		StatPanel			opponentStats;
	private 	  		Image					pictureOfOpponent;
	
	protected				Sound 										sound 					= new Sound();
	
	private TrumpSession	 	_session;
	
	/**
	 *  Declare the structure of the header in GWT Widget talk.
	 */
	public PlayGameView() {
		headerPanel.setWidth("100%");
		
		// Put an avatar representing the user on the LHS
		headerPanel.getCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getColumnFormatter().setWidth(0, "51px");
		
		// Then a score.
		headerPanel.getCellFormatter().setAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getColumnFormatter().setWidth(1, "51px");
		
		// Then an indicator as to whom is playing.
		headerPanel.getCellFormatter().setAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_MIDDLE);
		headerPanel.getColumnFormatter().setWidth(5, "51px");
		
		// This pane is where the win/lose is displayed.
		headerPanel.getCellFormatter().setAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
		headerPanel.getColumnFormatter().setWidth(3, "100%");
		
		// Again the indicator as to whom is playing.
		headerPanel.getCellFormatter().setAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		headerPanel.getColumnFormatter().setWidth(5, "51px");
		
		// Again the score.
		headerPanel.getCellFormatter().setAlignment(0, 5, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getColumnFormatter().setWidth(5, "51px");
		
		// And again the avatar.
		headerPanel.getCellFormatter().setAlignment(0, 6, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getColumnFormatter().setWidth(6, "51px");

		initWidget(headerPanel);
	}
	
	public PlayGameView(TrumpSession session) {
		this();
		_session = session;
	}

	public void doInit() {
		setHeader(this);		
	}
	
	
		public void setPlayer(Player player) {
			pictureOfPlayer = new Image(player.getPicture());
			playerStats = new StatPanel(player);
			playerStats.setStyleName("fbt-bluebg");
			headerPanel.setWidget(0, 0, pictureOfPlayer);
			headerPanel.setWidget(0, 1, playerStats);
		}
		
		public void setOpponent(Player opponent) {
			pictureOfOpponent = new Image(opponent.getPicture());
			opponentStats = new StatPanel(opponent);
			opponentStats.setStyleName("fbt-bluebg");
			headerPanel.setWidget(0, 6, pictureOfOpponent);
			headerPanel.setWidget(0, 5, opponentStats);
		}
		
		public void clearOpponent() {
			
		}
		
		private class StatPanel extends Composite {

			private final FlexTable 		statsPanel 	= new FlexTable();
			private  HTML playedWon  = new HTML("0");
			
			public StatPanel(Player jim) {
				HTML name = new HTML(jim.getName());
				name.setStyleName("fbt-large-bold");
				statsPanel.setWidget(1, 0, name);
				playedWon.setStyleName("fbt-questionmark");
				statsPanel.setWidget(0, 0, playedWon);
				
				initWidget(statsPanel);
			}
			
			public void setScore(int i){
				
				playedWon.setHTML(String.valueOf(i));
				statsPanel.setWidget(0, 0, playedWon);
			}

		}

		public void showWon() {
			HTML win = new HTML("You win!");
			win.setStyleName("fbt-wontext");
			headerPanel.setWidget(0, 3, win);
			sound.playSound("success03");
			Effector.appear(win);
			updateScores();
		}

		public void showLose() {
			HTML lose = new HTML("You lose");
			lose.setStyleName("fbt-wontext");
			headerPanel.setWidget(0, 3, lose);
			sound.playSound("uhoh");
			Effector.appear(lose);
			updateScores();		
		}

		public void showDraw() {
			HTML draw = new HTML("Draw");
			draw.setStyleName("fbt-wontext");
			headerPanel.setWidget(0, 3, draw);
			Effector.appear(draw);
			updateScores();		
		}
		
		private void updateScores() {
			
			playerStats.setScore(_session.getCurrentGame().getPlayerScore());
			opponentStats.setScore(_session.getCurrentGame().getOpponentScore());
		}
		
		public void playerTurn() {
			headerPanel.setWidget(0,2, new Image("img/arrow_left.gif"));
			headerPanel.setWidget(0,4, new HTML("&nbsp;"));
			headerPanel.setWidget(0,3, new HTML("&nbsp;"));
		}
		
		public void opponentTurn() {
			headerPanel.setWidget(0,2, new HTML("&nbsp;"));
			headerPanel.setWidget(0,4, new Image("img/arrow_right.gif"));
			headerPanel.setWidget(0,3, new HTML("&nbsp;"));
		}

	}
	
