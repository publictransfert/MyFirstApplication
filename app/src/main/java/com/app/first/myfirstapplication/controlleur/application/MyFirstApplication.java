package com.app.first.myfirstapplication.controlleur.application;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by hb on 03/01/2017.
 */
public class MyFirstApplication extends Application {

    private static Bus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();
        eventBus = new Bus();
    }

    public static Bus getEventBus() {
        return eventBus;
    }
}
