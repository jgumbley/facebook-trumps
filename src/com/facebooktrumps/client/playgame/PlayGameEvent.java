package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.Event;

public class PlayGameEvent extends Event {

	public static final int PLAYERMOVE = 1;
	public static final int NEXTTURN = 2;
	public static final int OPPONENTMOVE = 3;

	
	protected Integer _type;
	
	public PlayGameEvent(int a) {
		_type = new Integer(a);		
	}

	public PlayGameEvent() {
		// used for registration only.
		// need to find checks for abuse.
	}
	
	public Object getPayload() {
		return _type;
	}

	public boolean hasPayload() {
		if (_type != null) {
			return true;
		}
		else { return false; }
	}

	public void setPayload(Integer direction) {
		_type=  direction;
	}

}
