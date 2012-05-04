package com.facebooktrumps.client._root;

import com.facebooktrumps.client.Model;
import com.facebooktrumps.client.entities.GwtRpcService;
import com.facebooktrumps.client.entities.GwtRpcServiceAsync;
import com.facebooktrumps.client.entities.TrumpSession;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Contains RPC methods and global state.
 * 
 * Container model contains data that affects the whole application.
 * The model contains or holds the data objects that are to be displayed or worked upon in a view. 
 * Typically, the model receives a delegated data-service request from the controller, 
 * fetches the data, and notifies the associated view of the availability of fresh data. 
 * The model encapsulates key server-interaction functionality to retrieve data. 
*  The role of the model tends to be that of a data conduit that hides the complexity of data fetching 
*  and decouples the rest of the client tier from server-side data formats. 
*
 * @author Jim
 *
 */
public class RootModel extends Model {
    
	private final static 		GwtRpcServiceAsync 	service 			= (GwtRpcServiceAsync) GWT.create(GwtRpcService.class);
	private final static		String				RpcEndPoint		
											//					= "http://localhost/facebookTrumps/RpcEndpoint.html"; 
										        					= "http://www.facebooktrumps.com/game/RpcEndpoint.html";	
	private final 				ServiceDefTarget 	target 			= (ServiceDefTarget) service;
	private 					Timer				timer ;
	
	private 					TrumpSession	 	_session;
	
	public RootModel(TrumpSession session) {
		_session = session;
	}
	
	public void doInit() {
		// nice little pause might fix problem in safari.
		timer = new Timer() {
		      public void run() {
		  		getTrumpStack();
		      }
		    };
		 timer.schedule(1000);
	}


	private void getTrumpStack() {
		// Setup RPC
		target.setServiceEntryPoint(RpcEndPoint);
		
		// Get Trump Stack for this authenticated user:
		service.getLoggedInUserTrumpStack(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				fireEvent(new RootControllerEvent(RootControllerEvent.RPCERROR));
				}
			public void onSuccess(Object result) {
				// Store Trump Stack in Model
				TrumpSession r;
				try {
					 r =(TrumpSession) result;
					    _session.setPlayer(r.getPlayer());
					    _session.setPlayerStack(r.getPlayerStack());
					    
					  fireEvent(new RootControllerEvent(RootControllerEvent.CREATEGAME));
				} catch (NullPointerException e) {
				    	//fireEvent(new RootControllerEvent(RootControllerEvent.RPCERROR));
					   RootPanel.get("mainPanel").clear();
					   RootPanel.get("mainPanel").add(new HTML("error"));
				}
			}});
	}
}

		


