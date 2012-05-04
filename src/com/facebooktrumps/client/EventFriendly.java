package com.facebooktrumps.client;


public interface EventFriendly //SQ: Rename to Channel
{
    public void fireEvent(Event event);
    public void register(Event eventClass, EventListener listener);
}
