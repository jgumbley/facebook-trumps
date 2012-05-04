package com.facebooktrumps.client;

import com.facebooktrumps.client._root.RootController;
import com.facebooktrumps.client._root.RootModel;
import com.facebooktrumps.client._root.RootView;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.core.client.EntryPoint;

/**
 * GWT specific entry point method. Creates a top level HMVC/MVP triad and a current session,
 * then goes home. Nice and neat.
 * 
 * @author jimgumbley
 */
public class ui implements EntryPoint {
	
	public void onModuleLoad() {
	
		// Create a session object.
		TrumpSession _session = new TrumpSession();
		
		// Nice and neat.
		View 			view			= new RootView();
		Model 		model 		= new RootModel(_session);
		Controller 	controller 	= new RootController(_session);
		
		// Job done.
		new Triad(model, view, controller); 
  }

}