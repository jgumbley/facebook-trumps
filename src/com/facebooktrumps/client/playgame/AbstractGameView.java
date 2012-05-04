package com.facebooktrumps.client.playgame;

import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.Sound;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;

public class AbstractGameView extends EventCapableComposite {
	
	protected final 	FlexTable 									gamePanel 			= new FlexTable();
	protected				Timer											timer ;
	protected				Sound 										sound 					= new Sound();
	
	public AbstractGameView() {
	 	gamePanel.setWidth("100%");
		gamePanel.getCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_MIDDLE);
		gamePanel.getCellFormatter().setAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		gamePanel.getCellFormatter().setWidth(0, 1, "50%"); gamePanel.getCellFormatter().setWidth(0, 0, "50%");
		initWidget(gamePanel);
	}
	
	protected void setPlayerTrump(Widget w) {
		gamePanel.setWidget(0, 0, w);
	}
	
	protected void setOpponentTrump(Widget w) {
		gamePanel.setWidget(0, 1, w);
	}

}
