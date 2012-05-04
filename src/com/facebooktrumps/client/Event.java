package com.facebooktrumps.client;

import com.google.gwt.core.client.GWT;

public class Event
{
    private Object _payload;

    public Event(final Object payload)
    {
        _payload = payload;
    }

    public Event()
    {
    }

    public boolean equals(final Object obj)
    {
        String passedEventType = getEventType(obj);
        String localEventType = getEventType(this);
    	return passedEventType.matches(localEventType);
    }

    public Object getNullCheckedPayload()
    {
        return _payload == null ? "" : _payload;
    }

    public Object getPayload()
    {
        return _payload;
    }

    public void setPayload(final Object payload)
    {
        _payload = payload;
    }

    public boolean hasPayload()
    {
        return _payload != null;
    }

    public String toString() {
        return getEventType(this);
    }
    
    public static String getEventType(Object obj) {
    	return GWT.getTypeName(obj);
    }
}