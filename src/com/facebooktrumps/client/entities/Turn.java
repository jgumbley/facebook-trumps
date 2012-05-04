package com.facebooktrumps.client.entities;

import java.io.Serializable;

/**
 * This class reprensents a turn in the game.
 *
 */
public class Turn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4304623098095768477L;
	public static final int INTERESTS = 1;
	public static final int AGE = 2;
	public static final int WALLCOUNT = 3;
	public static final int EDUCATION = 4;
	
	private boolean userPlaying;
	private boolean played;
	private int _move;
	private int won;
	
	public int getMove() {
		return _move;
	}

	public void setMove(int _move) {
		this._move = _move;
	}

	public boolean isUserPlaying() {
		return userPlaying;
	}

	public void setUserPlaying(boolean userPlaying) {
		this.userPlaying = userPlaying;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public int isWon() {
		return won;
	}

	public void setWon(int won2) {
		this.won = won2;
	}
	
}