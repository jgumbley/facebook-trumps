package com.facebooktrumps.client.widgets;

import com.facebooktrumps.client.entities.Player;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class PlayerPane extends Composite {

	private final FlexTable 		statsPanel 	= new FlexTable();
		
	public PlayerPane(Player player) {
		HTML name = new HTML(player.getName());
		name.setStyleName("fbt-large-bold");
		statsPanel.setWidget(0, 0, name);
		statsPanel.setWidget(1, 0, new Image(player.getPicture()));
		
		initWidget(statsPanel);
	}
	
}
