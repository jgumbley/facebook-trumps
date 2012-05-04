package com.facebooktrumps.client._root;

import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.Loading;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Practically none - contains "Loading..." view.
 * @author Jim
 *
 */
public class RootView extends EventCapableComposite implements View {

	public void doInit() {
		Loading loadingWidget = new Loading();
		RootPanel.get("mainPanel").add(loadingWidget);
		loadingWidget.appear();
	}
	
}
