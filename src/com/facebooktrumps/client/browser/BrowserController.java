package com.facebooktrumps.client.browser;

import com.facebooktrumps.client.Controller;
import com.facebooktrumps.client.Event;
import com.facebooktrumps.client.EventListener;
import com.facebooktrumps.client.UpdateViewEvent;
import com.facebooktrumps.client.widgets.HeaderPaneView;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Just a controller passing messages. No child triads.
 * 
 * @author jimgumbley
 *
 */
public class BrowserController extends Controller {
	
	public void doInit() {
		RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(((Widget) this.getView()));
		
		RootPanel.get("header").clear();
		RootPanel.get("header").add(new HeaderPaneView());
		
		addUpdateTrumpBrowserViewListener();
		fireEvent(new BrowserEvent(BrowserEvent.START));
	}	
	
	private void addUpdateTrumpBrowserViewListener() {
		register(new UpdateViewEvent(), new EventListener() {
			public void handleEvent(Event event) {
				((BrowserView) getView()).updateView();
			}
		});
	}
		

}
