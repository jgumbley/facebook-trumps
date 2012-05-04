package com.facebooktrumps.client.browser;

import com.facebooktrumps.client.Event;
import com.facebooktrumps.client.EventListener;
import com.facebooktrumps.client.Model;
import com.facebooktrumps.client.UpdateViewEvent;
import com.facebooktrumps.client.entities.TrumpSession;


public class BrowserModel extends Model {
	
	private TrumpSession 	_session;
	
	public BrowserModel() {
	}

	public BrowserModel(TrumpSession session) {
		_session = session;
	}

	public void doInit() {
		// Set current trump to first in stack.
		_session.setBrowserCurrentTrump(0);
		_session.setCurrentGame(null);
		_session.getPlayerStack().addAll(_session.getOpponentStack());
		_session.setOpponentStack(null);
		
		// Register events this model object can handle:
		addBrowserEventListener();
		
		super.doInit();
	}

	private void addBrowserEventListener() {
		_controller.register(new BrowserEvent(), new EventListener() {
			public void handleEvent(Event event) {
				int signal = ((Integer) event.getPayload()).intValue();
				int _currentTrump = _session.getBrowserCurrentTrump();
				// Make required amendment to the current view.
				if (signal == BrowserEvent.START) {
					_currentTrump = 0;
				} else if ((signal == BrowserEvent.PREVIOUS)) {
					_currentTrump--;
					if (_currentTrump < 0) _currentTrump = 0;
				} else if ((signal == BrowserEvent.NEXT)) {
					_currentTrump++;
					if (_currentTrump >= _session.getPlayerStack().size()) _currentTrump = _session.getPlayerStack().size()-1;
				} else if ((signal == BrowserEvent.LAST)) {
					_currentTrump = _session.getPlayerStack().size()-1;
				}
				
				_session.setBrowserCurrentTrump(_currentTrump);
				_controller.fireEvent(new UpdateViewEvent());
			}
		});
	}
	

}
