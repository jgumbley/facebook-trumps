package com.facebooktrumps.client;


public class NullController extends Controller
{
    public void fireEvent(final Event event)
    {
		fireEventToChildren(event);
	}

	public boolean isNull()
    {
        return true;
    }
}