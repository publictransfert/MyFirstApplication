package com.app.first.myfirstapplication.model.dao.bdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.first.myfirstapplication.controlleur.application.MyFirstApplication;
import com.app.first.myfirstapplication.model.beans.EleveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb on 04/01/2017.
 */
public class EleveBddManager {

    public static final String TABLE_ELEVE = "Eleve";
    private static final String COL_ID = "ID";
    private static final String COL_PRENOM = "Prenom";
    private static final String COL_NOM = "Nom";
    public static final String CREATE_ELEVE_TABLE = "CREATE TABLE " + TABLE_ELEVE + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_PRENOM + " TEXT NOT NULL, " + COL_NOM + " TEXT NOT NULL);";

    public static void insertEleve(EleveBean eleve) {
        //Ouvrir la base en écriture
        SQLiteDatabase bdd = MyFirstApplication.getMaBaseSqlite().getWritableDatabase();
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_PRENOM, eleve.getPrenom());
        values.put(COL_NOM, eleve.getNom());
        //on insère l'objet dans la BDD via le ContentValues
        eleve.setId(bdd.insert(TABLE_ELEVE, null, values));
        if (eleve.getId() == -1) {
            //gestion erreur
        }
        bdd.close();
    }

    public static List<EleveBean> getAllEleves() {
        SQLiteDatabase bdd = MyFirstApplication.getMaBaseSqlite().getWritableDatabase();
        Cursor c = bdd.query(TABLE_ELEVE,
                new String[]{COL_ID, COL_PRENOM, COL_NOM},
                null, null, null, null, null);
        List<EleveBean> result = cursorToEleves(c);
        bdd.close();
        return result;
    }

    //Cette méthode permet de convertir un cursor en list d'Eleve
    private static List<EleveBean> cursorToEleves(Cursor c) {
        ArrayList<EleveBean> eleveListe = new ArrayList<EleveBean>();
        if (c != null) {
            //On se place sur le premier élément
            if (c.moveToFirst()) {
                do {
                    EleveBean eleveBean = new EleveBean(c.getString(c.getColumnIndex(COL_NOM)), c.getString(c.getColumnIndex(COL_PRENOM)), c.getLong(c.getColumnIndex
                            (COL_ID)));
                    eleveListe.add(eleveBean);
                }
                while (c.moveToNext());
            }
        }
        //On ferme le cursor
        c.close();
        //On retourne la liste
        return eleveListe;
    }

    public static int updateEleve(EleveBean eleve) {
        //Ouvrir la base en écriture
        SQLiteDatabase bdd = MyFirstApplication.getMaBaseSqlite().getWritableDatabase();
        //La mise à jour d'un élève dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel élève on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_PRENOM, eleve.getPrenom());
        values.put(COL_NOM, eleve.getNom());
        int result = bdd.update(TABLE_ELEVE, values, COL_ID + " = " + eleve.getId(),
                null);
        bdd.close();
        return result;
    }

    public static int removeEleveWithID(long id) {
        SQLiteDatabase bdd = MyFirstApplication.getMaBaseSqlite().getWritableDatabase();
        //Suppression d'un élève de la BDD grâce à l'ID
        int result = bdd.delete(TABLE_ELEVE, COL_ID + " = " + id, null);
        bdd.close();
        return result;
    }
}

