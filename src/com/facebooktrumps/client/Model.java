package com.facebooktrumps.client;


public class Model extends AbstractInitializable implements EventFriendly
{
    protected Controller _controller;

    public void setController(final Controller controller)
    {
        _controller = controller;
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
        _controller.register(eventClass.toString(), listener);
    }


}