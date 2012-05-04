package com.facebooktrumps.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class ConcealedTrump extends Composite {
	
	public ConcealedTrump() {
		Image trump = new Image("img/qm.jpg");
		trump.setPixelSize(238, 336);
		initWidget(trump);
	}
}
