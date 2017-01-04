package com.app.first.myfirstapplication.controlleur.application;

import android.app.Application;

import com.app.first.myfirstapplication.model.dao.bdd.MaBaseSqlite;
import com.squareup.otto.Bus;

/**
 * Created by hb on 03/01/2017.
 */
public class MyFirstApplication extends Application {

    private static Bus eventBus;
    private static MaBaseSqlite maBaseSqlite;

    @Override
    public void onCreate() {
        super.onCreate();
        eventBus = new Bus();
        maBaseSqlite = new MaBaseSqlite(this);
    }

    public static MaBaseSqlite getMaBaseSqlite() {
        return maBaseSqlite;
    }

    public static Bus getEventBus() {
        return eventBus;
    }
}
