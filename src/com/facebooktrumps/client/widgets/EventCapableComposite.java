package com.facebooktrumps.client.widgets;

import com.facebooktrumps.client.Controller;
import com.facebooktrumps.client.Event;
import com.facebooktrumps.client.EventListener;
import com.facebooktrumps.client.entities.TrumpSession;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class EventCapableComposite extends Composite implements View {

	protected TrumpSession _session;
    public String _uniqueId = "";
    protected Controller _controller;
    public boolean _disposed;

   /**
    * Method to embed passed widget in top of frame.
    * @param w
 * @return 
 * not sure how much I like this.
    */
   protected void setMain(Widget w){
	   RootPanel.get("mainPanel").clear();
		RootPanel.get("mainPanel").add(w);
   }
   
   protected void setHeader(Widget w){
	   RootPanel.get("header").clear();
		RootPanel.get("header").add(w);
   }   
    
    public EventCapableComposite()
    {
    }

    public EventCapableComposite(final String uniqueId)
    {
        _uniqueId = uniqueId;
    }

    public void setController(final Controller controller)
    {
        _controller = controller;
    }

    public void putChildInMap(final String uniqueID, final View view)
    {

    }

    public String getUniqueID()
    {
        return _uniqueId;
    }


    public View getChild(final String id)
    {
		return null;

    }

    public void dispose()
    {
        _disposed = true;
    }

    public boolean isDisposed()
    {
        return _disposed;
    }

    public void init()
    {
        pre();
        doInit();
        post();
    }

    public void pre()
    {

    }

    public void doInit()
    {

    }

    public void post()
    {

    }

    public Controller getController()
    {
        return _controller;
    }

    public void fireEvent(final Event event)
    {
        _controller.fireEvent(event);
    }

    public void register(final Event eventClass, final EventListener listener)
    {
        _controller.register(eventClass, listener);
    }

}
