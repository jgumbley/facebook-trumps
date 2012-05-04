package com.facebooktrumps.client.entities;

import java.io.Serializable;


/**
 * Sort of app specific data type which is propogated down the app.
 * 
 * @author jimgumbley
 *
 */
public class TrumpSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8223852074950394518L;
	
	/**
	 * An arraylist but sort of with a different name.
	 * @gwt.typeArgs <com.facebooktrumps.client.model.entities.Trump>
	 */
	TrumpStack 	playerStack;
	
	/**
	 * An arraylist but sort of with a different name.
	 * @gwt.typeArgs <com.facebooktrumps.client.model.entities.Trump>
	 */
	TrumpStack 	opponentStack;
	
	Game currentGame;
	
	boolean toss;
	
	Player 		player;
	Player		opponent;
	
	int			browserCurrentTrump;
	
	public TrumpStack getPlayerStack() {
		return playerStack;
	}


	public void setPlayerStack(TrumpStack playerStack) {
		this.playerStack = playerStack;
	}

	public int getBrowserCurrentTrump() {
		return browserCurrentTrump;
	}

	public void setBrowserCurrentTrump(int browserCurrentTrump) {
		this.browserCurrentTrump = browserCurrentTrump;
	}


	public Player getOpponent() {
		return opponent;
	}


	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public TrumpStack getOpponentStack() {
		return opponentStack;
	}


	public void setOpponentStack(TrumpStack opponentStack) {
		this.opponentStack = opponentStack;
	}


	public Game getCurrentGame() {
		return currentGame;
	}


	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}


	public boolean isToss() {
		return toss;
	}


	public void setToss(boolean toss) {
		this.toss = toss;
	} 
	
}
