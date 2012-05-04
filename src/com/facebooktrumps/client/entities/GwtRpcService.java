package com.facebooktrumps.client.entities;

import com.google.gwt.user.client.rpc.RemoteService;

public interface GwtRpcService extends RemoteService {
	
	public String getUserName();
	
	public TrumpSession getLoggedInUserTrumpStack();
	

}
