package com.app.first.myfirstapplication.model.dao.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hb on 04/01/2017.
 */
public class MaBaseSqlite extends SQLiteOpenHelper {

    private static final String NOM_BDD = "myFirstBdd.db";
    private static final int VERSION_BDD = 1;

    public MaBaseSqlite(Context context) {
        super(context, NOM_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_ELEVE_TABLE
        sqLiteDatabase.execSQL(EleveBddManager.CREATE_ELEVE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Copier la base de donnée de l'application dans le repertoire download
     */
    public static void CopySQLiteBaseToDownload(Context context, String tableName) {
        //OU se trouve la base de donnée
        File database = new File("data/data/" + context.getPackageName() + "/databases/" + tableName);
        //Ou on la copie
        File downloadDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + tableName);
        try {
            if (database.exists()) {
                if (!downloadDirectory.exists()) {
                    downloadDirectory.createNewFile();
                }
                InputStream in = new FileInputStream(database);
                OutputStream out = new FileOutputStream(downloadDirectory);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Toast.makeText(context, "Le fichier a été copié", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, "Erreur lors de la copie", Toast.LENGTH_LONG).show();
            }
            //Permet de le voir directement dans windows
            MediaScannerConnection.scanFile(context, new String[]{downloadDirectory.getAbsolutePath()}, null, null);
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Erreur lors de la copie", Toast.LENGTH_LONG).show();
        }
    }
}
