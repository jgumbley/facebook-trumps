package com.facebooktrumps.client.entities;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface GwtRpcServiceAsync extends RemoteService {
	
	public void getUserName(AsyncCallback callback);
	
	public void getLoggedInUserTrumpStack(AsyncCallback callbacks);

}
