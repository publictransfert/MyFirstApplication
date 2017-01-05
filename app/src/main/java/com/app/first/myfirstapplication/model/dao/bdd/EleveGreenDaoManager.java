package com.app.first.myfirstapplication.model.dao.bdd;

import com.app.first.myfirstapplication.controlleur.application.MyFirstApplication;

import java.util.ArrayList;

import javapackage.Eleve;
import javapackage.EleveDao;

/**
 * Created by hb on 04/01/2017.
 */
public class EleveGreenDaoManager {

    public static void insertOrUpdate(Eleve eleve) {
        getEleveDao().insertOrReplace(eleve);
    }

    public static void clearEleve() {
        getEleveDao().deleteAll();
    }

    public static void deleteEleveWithId(long id) {
        getEleveDao().delete(getEleveForId(id));
    }

    public static Eleve getEleveForId(long id) {
        return getEleveDao().load(id);
    }

    public static ArrayList<Eleve> getAllEleve() {
        return (ArrayList<Eleve>) getEleveDao().loadAll();
    }

    private static EleveDao getEleveDao() {
        return MyFirstApplication.getInstance().getDaoSession().getEleveDao();
    }
}
