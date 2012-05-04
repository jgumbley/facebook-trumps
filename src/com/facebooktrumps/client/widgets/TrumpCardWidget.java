package com.facebooktrumps.client.widgets;

import com.facebooktrumps.client.entities.Trump;
import com.facebooktrumps.client.entities.Turn;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;

/**
 *  Widget which looks like a trump card for representing trumps.
 *  
 *  The fields are public so other classes can add clicklisteners to it.
 *  A bit grubby but hey.
 *  
 * @author Jim
 *
 */
public class TrumpCardWidget extends EventCapableComposite implements View {
		
	private final FlowPanel 			card		= new FlowPanel();
	private final FlowPanel				body		= new FlowPanel();
	
	public final Field	interests  	= new Field( false, "Interests");
	public final Field	wallcount  	= new Field( true, "Wallcount");
	public final Field	age  			= new Field( false, "Age");
	public final Field	education 	= new Field( true, "Education");
	
	private final HTML	 						name = new HTML() ;
	private final Image							picture = new Image();
	
	protected Sound 						sound = new Sound();
	protected Trump 						thisCard;
	
	public TrumpCardWidget() {
		// instantiate
		initWidget(generateCard());
	}	
	
	public TrumpCardWidget(Trump T) {	
		this();
		populateCard(T);
	}
	
	/**
	 * Puts the specified trump values into the trump view.
	 */
	public  void populateCard(Trump t){
		name.setHTML("<h2>" + t.getPersonsName() + "</h2>");
		picture.setUrlAndVisibleRect(t.getPhotoUrl(), 0, 0, 200, 170);
		
		interests.setValue(t.getInterests().toString());
		wallcount.setValue(t.getWallcount().toString());
						
		if (t.getAge().intValue() == 0) age.setValue("n/a");
		else 											age.setValue(t.getAge().toString());
		
		education.setValue(t.getEducation().toString());	
	}
	
	/**
	 * Initialisation code.
	 * @return
	 */
	private Widget generateCard(){
		
		card.addStyleName("trump");
		name.addStyleName("trump_head");
		body.addStyleName("trump_body");
		
		FlexTable s = new FlexTable();
		s.addStyleName("fbt-aligncentre");
		s.setWidget(0,0, picture);
		
		body.add(picture);	
		body.add(interests);
		body.add(wallcount);
		body.add(age);
		body.add(education);
		body.add(new HTML("&nbsp;"));
		
		card.add(name);
		card.add(body);
		
		return card;
	}
	
	/**
	 * Inner class to encapsulate the GUIness of the different fields.
	 * @author Jim
	 *
	 */
	public class Field extends FocusPanel  {
		
		private boolean _odd;
		private FlexTable 		table = new FlexTable();
		
		public void setValue(String value){
			table.setWidget(0, 1, new HTML(value)); 			
		}
		
		public Field() {
			add(table);
		}
		
		public Field(boolean odd, String field){
			this();
			_odd = odd;
			table.setStyleName("fbt-stattable");
			table.setWidget(0, 0, new HTML(field)); 
			
			Format();
		}
		
		/**
		 * Little method to format the row appropriately, whether for the first or second time.
		 */
		private void Format() {
			if (_odd) {
				table.getCellFormatter().setStyleName(0, 0, "fbt-stattable-odd-s"); 
				table.getCellFormatter().setStyleName(0, 1, "fbt-stattable-odd");
			} else {
				table.getCellFormatter().setStyleName(0, 0, "fbt-stattable-even-s");
				table.getCellFormatter().setStyleName(0, 1, "fbt-stattable-even");			
			}
		}
		
		/**
		 * If a click listner is defined then selection highlighting gets enabled...
		 * I'm sort of half pleased half uneasy with this...
		 * @param clickListener
		 */
		public void addClickResponse(final ClickListener clickListener) {
			addMouseListener(new MouseListenerAdapter() {
				public void onMouseEnter(Widget sender) {
					table.getCellFormatter().setStyleName(0, 0, "fbt-stattable-sel-s"); 
					table.getCellFormatter().setStyleName(0, 1, "fbt-stattable-sel");
					sound.playSound("smoothrollover");
				}

				public void onMouseLeave(Widget sender) {
					Format();
				}
			
			});
			
			addClickListener(clickListener);
		}

		public void highlight() {
			table.getCellFormatter().setStyleName(0, 0, "fbt-stattable-sel-s"); 
			table.getCellFormatter().setStyleName(0, 1, "fbt-stattable-sel");
		}
		
	}

	public void highlightField(int select) {
		 switch (select) {
		 		case Turn.AGE: age.highlight(); break;
		 		case Turn.EDUCATION: education.highlight(); break;
		 		case Turn.INTERESTS: interests.highlight(); break; 
		 		case Turn.WALLCOUNT: wallcount.highlight(); break;
		 }
     		
		
	}
 

}
