package com.facebooktrumps.client.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
* Broadly, the game is played with a stack of cards where each card displays a set of unique 
* statistical values representing an individual entity- the statistics shown are uniform and 
* comparable between cards. 
* 
* The game begins where the cards are shuffled and dealt between two or more players. A 
* player then takes a turn, selecting the most fortuitous looking statistic on the top card of their 
* own deck. Each other player declares their own value for that statistic, the player with the highest 
* value winning the round. The winner of the round takes the losing cards of all the other players 
* and places them on the bottom of their deck. The next player will then take their turn. You then
* lose by running out of cards. 
* 
* You win by collecting the entire pack.
 * @author Jim
 */
public class Game implements Serializable {
	
	private static final long serialVersionUID = 7779529229358249926L;
	
	// Game Stuff:
	private int currentTurn = -1;
	
	/**
	 * @gwt.typeArgs <com.facebooktrumps.client.model.entities.Turn>
	 */
	private ArrayList turns = new ArrayList();
	
	// Should be methods:
	private int playerScore = 0;
	private int opponentScore =0;
	
	public int getPlayerScore() {
		return playerScore;
	}

	public int getOpponentScore() {
		return opponentScore;
	}
	
	public void incrementPlayerScore() {
		playerScore++;
	}

	public void incrementOpponentScore() {
		opponentScore++;
	}
	
	public Game() {
		nextTurn();
	}
	
	// Current Move
	public void nextTurn(){
		turns.add(new Turn());
		currentTurn++;
	}
	
	// Getters/Setters etc:
	
	public int getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	
	// From the current move:
	public boolean isUserPlaying() {
		return ((Turn) turns.get(currentTurn)).isUserPlaying();
	}
	public void setUserPlaying(boolean userPlaying) {
		 ((Turn) turns.get(currentTurn)).setUserPlaying(userPlaying);
	}
	public boolean isPlayed() {
		return ((Turn) turns.get(currentTurn)).isPlayed();
	}
	public void setPlayed(boolean played) {
		 ((Turn) turns.get(currentTurn)).setPlayed(played);
	}
	
	public int hasWon() {
		return ((Turn) turns.get(currentTurn)).isWon();
	}
	
	public void setWon(int won) {
		((Turn) turns.get(currentTurn)).setWon(won);
	}
	
	public int getMove() {
		return ((Turn) turns.get(currentTurn)).getMove();
	}
	
	public void setMove(int move) {
		((Turn) turns.get(currentTurn)).setMove(move);
	}
	
	


	
}
