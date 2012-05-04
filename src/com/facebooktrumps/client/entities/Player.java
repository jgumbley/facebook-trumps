package com.facebooktrumps.client.entities;

import java.io.Serializable;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 906075081321638387L;
	
	private String 		name;
	private int 		wins;
	private int 		gamesPlayed;
	private boolean		availible;
	private String 		picture;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public boolean isAvailible() {
		return availible;
	}
	public void setAvailible(boolean availible) {
		this.availible = availible;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
		
}
