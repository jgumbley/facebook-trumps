package com.facebooktrumps.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;

/**
 * Nice little reusable widget to show a loading animation.
 * @author Jim
 *
 */
public class Loading extends Composite {
	
	public Loading() {
		// Self is the loading animation.
		FlexTable table = new FlexTable();
		table.setWidth("100%"); table.setHeight("100%");
		
	    table.getCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
	    		
		Image loading = new Image("img/loading.gif");
		loading.setPixelSize(66, 66);
		loading.setStyleName("fbt-aligncentre");
		
		table.setWidget(0, 0, loading);
		initWidget(table);
		appear();
	}
	
	
	public void appear() {
		Effector.appear(this);
	}
	



}
