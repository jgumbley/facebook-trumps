package com.facebooktrumps.client;


public class AbstractInitializable implements Initializable
{
    private boolean _initialized;

    final public void init()
    {
        if(!_initialized)
            performInit();
    }

    private void performInit()                                  
    {
        pre();
        doInit();
        post();
        setInitialised();
    }

    private void setInitialised()
    {
        _initialized=true;
    }

    public void resetInitialised()
    {
        _initialized = false;
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
}