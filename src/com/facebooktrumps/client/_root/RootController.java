package com.facebooktrumps.client._root;

import com.facebooktrumps.client.Controller;
import com.facebooktrumps.client.Event;
import com.facebooktrumps.client.EventListener;
import com.facebooktrumps.client.Model;
import com.facebooktrumps.client.Triad;
import com.facebooktrumps.client.browser.BrowserController;
import com.facebooktrumps.client.browser.BrowserModel;
import com.facebooktrumps.client.browser.BrowserView;
import com.facebooktrumps.client.creategame.CreateGameController;
import com.facebooktrumps.client.creategame.CreateGameModel;
import com.facebooktrumps.client.creategame.CreateGameView;
import com.facebooktrumps.client.endgame.EndGameController;
import com.facebooktrumps.client.endgame.EndGameView;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.playgame.PlayGameController;
import com.facebooktrumps.client.playgame.PlayGameModel;
import com.facebooktrumps.client.playgame.PlayGameView;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Co-ordinates transistions from one Triad to another.
 * 
 * Following the mediator pattern.
 * @author Jim
 *
 */
public class RootController extends Controller {
	
	private TrumpSession	 	_session;
	
	private Triad 			 	activeTriad;

	public RootController(TrumpSession session) {
		_session = session;
		addRootControllerEventListener();
	}
	
	/**
	 * Transition between top level triad event listener.
	 */
	private void addRootControllerEventListener() {
		register(new RootControllerEvent(), new EventListener(){
			public void handleEvent(Event event) {
				// Take appropriate action:
				switch (((Integer) event.getPayload()).intValue()) {
		 			case RootControllerEvent.CREATEGAME: 
		 				initialiseCreateGameTriad();
		 				break;
		 			case RootControllerEvent.PLAYGAME: 
		 				initialisePlayGameTriad();
		 				break;
		 			case RootControllerEvent.ENDGAME: 
		 				initialiseEndGameTriad();
		 				break;
		 			case RootControllerEvent.RPCERROR:
		 			    RootPanel.get("mainPanel").clear();
		 			    RootPanel.get("mainPanel").add(new HTML("RPC Failure"));
		 				break;
		 			case RootControllerEvent.TRUMPBROWSER:
		 				initialiseTrumpBrowserTriad();
		 				break;
				}
			}
		});		
	}
	
	/**
	 * Initialise Create Game HMVC Triad
	 */
	private void initialiseCreateGameTriad() {	
		cleanUp();
		activeTriad	 = 		new Triad(		
									RootController.this, 						// Parent, this.
									new CreateGameModel(_session), 		// Model.
									new CreateGameView(_session),   		// View.
									new CreateGameController());				// Controller.
		
	}

	/**
	 * Initialise Game HMVC Triad
	 */
	private void initialisePlayGameTriad() {
		cleanUp();
		activeTriad =		 new Triad(		
									RootController.this, 										// Parent, this.
									new PlayGameModel(_session),				// Model
									new PlayGameView(_session), 					// View
									new PlayGameController(_session));		// Controller	
	}

	/**
	 * Initialise End Game HMVC Triad
	 */
	private void initialiseEndGameTriad() {
		cleanUp();
		activeTriad = 		new Triad(
									RootController.this,											// Parent Controller
									new Model(),														// Null Model
									new EndGameView(_session),									// View
									new EndGameController());							// Controller
	}
	
	/**
	 * Initialise Trump Browser HMVC Triad
	 */
	private void initialiseTrumpBrowserTriad() {
		cleanUp();
		activeTriad =		 new Triad(		
									RootController.this,											// Parent, this.
									new BrowserModel(_session), 					// Model
									new BrowserView(_session), 						// View
									new BrowserController());								// Controller	
	}
	
	/**
	 * Destroy all child HMVC triads, removes any existing modules before creating the new one.
	 */
	private void cleanUp() {
		if (activeTriad != null) activeTriad.destroy();
		activeTriad = null;
	}

}
