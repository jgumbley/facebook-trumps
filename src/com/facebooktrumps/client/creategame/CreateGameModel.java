package com.facebooktrumps.client.creategame;

import com.facebooktrumps.client.CardUtility;
import com.facebooktrumps.client.Model;
import com.facebooktrumps.client.entities.Game;
import com.facebooktrumps.client.entities.Player;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.entities.TrumpStack;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Instantiate Game, Set players, Shuffle cards, Deal Cards, Toss Coin.
 * @author Jim
 *
 */
public class CreateGameModel extends Model {
	
	private TrumpSession	 	_session;
	
	public CreateGameModel(TrumpSession session) {
		_session = session;
	}

	public void doInit(){
		createNewGame();
		setOpponent();
		dealPack();
		shuffleCards();
		toss();
	};

	private void createNewGame() {
		// Create a new game.
		Game newGame = new Game();
		_session.setCurrentGame(newGame);
	}
	
	private void setOpponent() { 
		// create CPU player
		Player robot = new Player();
		robot.setName("Robot");
		robot.setAvailible(true);
		robot.setGamesPlayed(10);
		robot.setWins(10);
		robot.setPicture("img/wobot.jpg");	
		_session.setOpponent(robot);
	}
	
	private void dealPack() {
		// Give opponent half cards.
		_session.setOpponentStack( _session.getPlayerStack().copy());
		_session.getOpponentStack().clearFirstHalf();
		_session.getPlayerStack().clearLastHalf();
	}
	
	private void shuffleCards() {
		// Shuffle the cards.
		CardUtility.shuffle(_session.getPlayerStack());	
		CardUtility.shuffle(_session.getOpponentStack());	
	}
				
	private void toss() {
		if (Random.nextBoolean()) {
			// Set player up to start first turn.
			_session.getCurrentGame().setUserPlaying(true);
		} else {
			_session.getCurrentGame().setUserPlaying(false);
		}
		_session.getCurrentGame().setPlayed(false);
	}
	

}
