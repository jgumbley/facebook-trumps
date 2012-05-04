package com.facebooktrumps.client;

public interface Initializable
{
    void init();

    void pre();

    void doInit();

    void post();
}