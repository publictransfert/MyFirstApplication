package com.app.first.myfirstapplication.controlleur.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.app.first.myfirstapplication.model.dao.bdd.MaBaseSqlite;
import com.squareup.otto.Bus;

import javapackage.DaoMaster;
import javapackage.DaoSession;

/**
 * Created by hb on 03/01/2017.
 */
public class MyFirstApplication extends Application {

    private static Bus eventBus;
    private static MaBaseSqlite maBaseSqlite;
    private DaoSession daoSession;
    private static MyFirstApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        eventBus = new Bus();
        maBaseSqlite = new MaBaseSqlite(this);
        instance = this;
        setupDatabase();
    }

    public static MyFirstApplication getInstance() {
        return instance;
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mygreentable-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static MaBaseSqlite getMaBaseSqlite() {
        return maBaseSqlite;
    }

    public static Bus getEventBus() {
        return eventBus;
    }
}
