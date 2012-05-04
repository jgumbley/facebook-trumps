package com.facebooktrumps.client.widgets;

import com.facebooktrumps.client.entities.Player;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;

public class HeaderPaneView extends Composite {
	
	private final FlexTable 		headerPanel 	= new FlexTable();
	private 	  Image 					pictureOfPlayer;
	
	public HeaderPaneView() {
		headerPanel.setWidth("100%");
		headerPanel.getCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
		
		headerPanel.getCellFormatter().setAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getCellFormatter().setAlignment(0, 3, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getCellFormatter().setAlignment(0, 4, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		headerPanel.getColumnFormatter().setWidth(4, "51px");
		
		Image title = new Image("img/title.gif");
		headerPanel.setWidget(0, 0, title);
		
		initWidget(headerPanel);
	}
	
	public void setPlayerBrowsing(Player player) {
		pictureOfPlayer = new Image(player.getPicture());
		HTML name = new HTML(player.getName() + "'s Trumps.");
		name.setStyleName("fbt-large-bold");
		headerPanel.setWidget(0, 4, pictureOfPlayer);
		headerPanel.setWidget(0, 3, name);
	}
	
	
}
