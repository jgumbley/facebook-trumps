package com.facebooktrumps.client.browser;

import com.facebooktrumps.client.Event;
import com.facebooktrumps.client._root.RootControllerEvent;
import com.facebooktrumps.client.entities.Trump;
import com.facebooktrumps.client.entities.TrumpSession;
import com.facebooktrumps.client.widgets.EventCapableComposite;
import com.facebooktrumps.client.widgets.Sound;
import com.facebooktrumps.client.widgets.TrumpCardWidget;
import com.facebooktrumps.client.widgets.View;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class BrowserView extends EventCapableComposite implements View {
	
	protected final FlexTable 		browserPanel 	= new FlexTable();
	protected final Image 			clickRight 		= new Image("img/resultset_previous.gif");
	protected final Image 			clickLeft 		= new Image("img/resultset_next.gif");
	protected final Image 			clickLast 		= new Image("img/resultset_last.gif");
	protected final Image 			clickFirst 		= new Image("img/resultset_first.gif");
	
	protected 		Widget 								trumpIndexWidget;
	protected 		TrumpCardWidget 			T;
	private 		Sound 									sound = new Sound();
	
	public BrowserView() {

		sound.playSound("card_shuffle");
		browserPanel.addStyleName("fbt-lightgreenbg");
		
		// Add right browser button
		browserPanel.setWidget(0, 0, clickFirst);
		clickFirst.addStyleName("fbt-linkimage");
		clickFirst.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Event trumpBrowserEvent = new BrowserEvent(BrowserEvent.START);
	        	fireEvent(trumpBrowserEvent);
			}
		});
		
		// Add right browser button
		browserPanel.setWidget(0, 1, clickRight);
		clickRight.addStyleName("fbt-linkimage");
		clickRight.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Event trumpBrowserEvent = new BrowserEvent(BrowserEvent.PREVIOUS);
	        	fireEvent(trumpBrowserEvent);
			}
		});
		
		// Add left browser button
		browserPanel.setWidget(0, 3, clickLeft);
		clickLeft.addStyleName("fbt-linkimage");
		clickLeft.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Event trumpBrowserEvent = new BrowserEvent(BrowserEvent.NEXT);
	        	fireEvent(trumpBrowserEvent);
			}
		});
		
		//		 Add last browser button
		browserPanel.setWidget(0, 4, clickLast);
		clickLast.addStyleName("fbt-linkimage");
		clickLast.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Event trumpBrowserEvent = new BrowserEvent(BrowserEvent.LAST);
	        	fireEvent(trumpBrowserEvent);
			}
		});
		
		Button homeButton = new Button("Play Game!");
		homeButton.addClickListener(new ClickListener() {
		    public void onClick(Widget sender) {
			// TODO Auto-generated method stub
			 fireEvent(new RootControllerEvent(RootControllerEvent.CREATEGAME));
		    }});
		homeButton.setStyleName("fbt-aligncentre");
		browserPanel.setWidget(1, 4, homeButton);
		
		initWidget(browserPanel);
	}
	
	public BrowserView(TrumpSession session) {
		this();	
		_session = session;	 
	}
	
	public void updateView() {
		sound.playSound("card_deal");
		T = new TrumpCardWidget((Trump) _session.getPlayerStack().get(_session.getBrowserCurrentTrump()));
		
		int currentTrump = _session.getBrowserCurrentTrump() + 1;
		String trumpIndex = new Integer(currentTrump).toString() + " / " + new Integer(_session.getPlayerStack().size()).toString();
		trumpIndexWidget = new HTML(trumpIndex);
		trumpIndexWidget.setStyleName("fbt-aligncentre");
		
		browserPanel.setWidget(0, 2, T);
		browserPanel.setWidget(1, 2, trumpIndexWidget);
		
		// Effects.Effect("Appear", T);
		
	}


}
