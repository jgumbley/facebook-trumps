package com.facebooktrumps.client.widgets;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;

public class FacebookStyleLink extends Composite {

	final HTML text;
	final Sound sound = new Sound();
		
	public FacebookStyleLink(String link) {
		text = new HTML(link);
		text.setStyleName("fbt-link");
		
		text.addMouseListener(new MouseListenerAdapter() {

			public void onMouseEnter(Widget sender) {
				text.setStyleName("fbt-link-hl");
				sound.playSound("smoothrollover");
				
			}

			public void onMouseLeave(Widget sender) {
				text.setStyleName("fbt-link");
			}
		
		});
		
		
		initWidget(text);
	}

	public void addClickListener(ClickListener clickListener) {
		text.addClickListener(clickListener);
	}
}
