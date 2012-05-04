package com.facebooktrumps.client.browser;

import com.facebooktrumps.client.Event;


public class BrowserEvent extends Event {

	public static final int START = 0;
	public static final int PREVIOUS = 1;
	public static final int NEXT = 2;
	public static final int LAST = 3;
	
	protected Integer _direction;
	
	public BrowserEvent(int a) {
		_direction = new Integer(a);		
	}

	public BrowserEvent() {
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
