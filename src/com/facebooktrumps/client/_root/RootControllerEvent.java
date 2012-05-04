package com.facebooktrumps.client._root;

import com.facebooktrumps.client.Event;


public class RootControllerEvent extends Event {

	public static final int CREATEGAME = 0;
	public static final int PLAYGAME = 1;
	public static final int ENDGAME = 2;
	public static final int RPCERROR = 3;
	public static final int TRUMPBROWSER = 4;

	
	protected Integer _direction;
	
	public RootControllerEvent(int a) {
		_direction = new Integer(a);		
	}

	public RootControllerEvent() {
		// used for registration only.
		// need to find checks for abuse.
	}
	
	public Object getPayload() {
		return _direction;
	}

	public boolean hasPayload() {
		if (_direction != null) {
			return true;
		}
		else { return false; }
	}

	public void setPayload(Integer direction) {
		_direction=  direction;
	}

}
