package com.facebooktrumps.client;

import com.facebooktrumps.client.widgets.View;
import com.google.gwt.core.client.GWT;

import java.util.*;

public class Controller extends AbstractInitializable
{
    protected List _children = new ArrayList();
    private Model _model = new Model();
    private View _view;
    protected Controller _parent;
    Map _events = new HashMap();
    private boolean _disposed = false;

    public void setParent(final Controller parent)
    {
        _parent = parent;
    }

    public Controller getParent()
    {
        return _parent;
    }

    public void setModel(final Model model)
    {
        _model = model;
    }

    public Model getModel()
    {
        return _model;
    }

    public void setView(final View view)
    {
        _view = view;
    }

    public View getView()
    {
        return _view;
    }

    public int getChildrenCount()
    {
        return isDisposed() ? 0 : _children.size();
    }

    public void addChildController(final Controller child)
    {
        child.setParent(this);
        _children.add(child);
    }

    public Controller getChildControllerAt(final int index)
    {
        return (Controller) _children.get(index);
    }

    public void notifyEventListeners(final Event event)
    {
        //SQ: This is hiding the bigger problem of dangling disposed controllers.
        if (!isDisposed())
            notifyEventListenersNoGuard(event);
    }

    private void notifyEventListenersNoGuard(final Event event)
    {
        final List customCollection = detectListenerCollection(event.toString());

        for (int i = 0; i < customCollection.size(); i++)
            ((EventListener) customCollection.get(i)).handleEvent(event);
    }

    public void register(final Event eventType, final EventListener listener)
    {
        register(eventType.toString(), listener);
    }

    public void register(final String eventType, final EventListener listener) {
        addMapEntryIfItDoesNotExist(eventType);
        addListenerToMapEntry(eventType, listener);
    }

    public void deregister(final Class eventType, final EventListener listener)
    {
        getEvents(eventType.toString()).remove(listener);
    }

    public void deregister(final Class eventType)
    {
        getEvents(eventType.toString()).clear();
    }

    private List getEvents(final String eventType)
    {
        return (List) _events.get(eventType);
    }

    public boolean hasEventType(final Class eventType)
    {
        return _events.containsKey(eventType.toString());
    }

    public boolean hasListenerType(final Class listenerType)
    {
        final Collection events = _events.values();
        for (Iterator iterator = events.iterator(); iterator.hasNext();)
            if (vectorContainsListenerOfType(((List) iterator.next()), listenerType))
                return true;
        return false;
    }

    private boolean vectorContainsListenerOfType(final List vector, final Class listenerType)
    {
    	String stringType = new StringBuffer(GWT.getTypeName(listenerType)).substring(6);
        for (int i = 0; i < vector.size(); i++)
            if (Event.getEventType((Event) vector.get(i)).equals(stringType))
                return true;
        return false;
    }

    public boolean hasListener(final EventListener listener)
    {
        return hasListener(listener, _events);

    }

    public static boolean hasListener(final EventListener listener, final Map eventMap)
    {
        final Collection events = eventMap.values();
        for (Iterator iterator = events.iterator(); iterator.hasNext();)
            if (((List) iterator.next()).contains(listener))
                return true;
        return false;
    }

    private void addListenerToMapEntry(final String eventType, final EventListener listener)
    {
        detectListenerCollection(eventType).add(listener);
    }

    private List detectListenerCollection(final String eventType)
    {
        final List customCollection = getEvents(eventType);
        return customCollection == null ? new ArrayList() : customCollection;
    }

    private void addMapEntryIfItDoesNotExist(final String eventString)
    {
        if (!_events.containsKey(eventString))
            _events.put(eventString, new ArrayList());
    }

    public void remove(final Controller controller)
    {
        _children.remove(controller);
    }

    public boolean isNull()
    {
        return false;
    }

    public void fireEvents(final Event[] events)
    {
        for (int i = 0; i < events.length; i++)
            fireEvent(events[i]);
    }

    public void fireEvent(final Event event, final boolean fireDownOnly)
    {
        if (!fireDownOnly && _parent != null)
            _parent.fireEvent(event);
        else
            fireEventDown(event);
    }

    public void fireEvent(final Event event)
    {
        fireEvent(event, false);
    }

    public final void fireEventDown(final Event event)
    {
        fireEventToChildren(event);
        notifyEventListeners(event);
    }

    protected void fireEventToChildren(final Event event)
    {
        //SQ: This is hiding the bigger problem of dangling disposed controllers.
        for (int i = 0; !isDisposed() && i < _children.size(); i++)
            notifyListenersAndRecurse(((Controller) _children.get(i)), event);
    }

    private void notifyListenersAndRecurse(final Controller controller, final Event event)
    {
        controller.notifyEventListeners(event);
        controller.fireEventToChildren(event);
    }

    public void dispose()
    {
        _disposed = true;
        if (_events != null)
            disposeNoGuard();
    }

    private void disposeNoGuard()
    {
        unhookFromParents();
        clearEventMap();
        nullify();
    }

    private void clearEventMap()
    {
        clearVectors();
        clearMap();
        _parent = null;
    }

    private void nullify()
    {
        _model = null;
        _children = null;
        _view = null;
        _events = null;
    }

    private void unhookFromParents()
    {
        if (_parent != null)
            _parent.remove(this);
        _parent = null;
    }

    private void clearMap()
    {
        _events.clear();
    }

    private void clearVectors()
    {
        for (Iterator iterator = _events.values().iterator(); iterator.hasNext();)
            ((List) iterator.next()).clear();
    }

    public boolean hasChild(final Controller controller)
    {
        return _children.contains(controller);
    }

    public Map getEvents()
    {
        return _events;
    }

    public void destroyContents()
    {

    }

    public boolean isDisposed()
    {
        return _disposed;
    }
}