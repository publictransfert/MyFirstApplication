package com.app.first.myfirstapplication.controlleur.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.at.LoadEleveAT;
import com.app.first.myfirstapplication.model.beans.EleveBean;
import com.app.first.myfirstapplication.model.dao.bdd.EleveBddManager;
import com.app.first.myfirstapplication.model.dao.bdd.MaBaseSqlite;
import com.app.first.myfirstapplication.vue.adapter.MyFirstRecycleViewAdapter;

import java.util.ArrayList;
import java.util.Random;

public class MyFirstBddActivity extends AppCompatActivity implements View.OnClickListener, MyFirstRecycleViewAdapter.RVCallBack {

    private Button btnAddEleve, btnExportBdd;
    // Données
    private ArrayList<EleveBean> desEleveBeen;
    private boolean showID = true;
    private MyFirstRecycleViewAdapter rVAdapter;
    // Composant graphique afficheur de RecyclerView.Adapter
    private RecyclerView recycleView;
    private MyFirstRecycleViewAdapter.RVCallBack callback;

    private final String EXTRA_LIST_ELEVE = "mes_eleves";

    private LoadEleveAT loadEleveAT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_bdd);
        btnAddEleve = (Button) findViewById(R.id.mfba_btn_add_eleve);
        btnAddEleve.setOnClickListener(this);
        btnExportBdd = (Button) findViewById(R.id.mfba_btn_export);
        btnExportBdd.setOnClickListener(this);
        // Création de la liste
        desEleveBeen = new ArrayList<EleveBean>();
        desEleveBeen.addAll(EleveBddManager.getAllEleves());
        // Instanciation d’un MyFirstRecycleViewAdapter
        rVAdapter = new MyFirstRecycleViewAdapter(desEleveBeen, this, MyFirstRecycleViewAdapter.TYPE_AFFICHAGE.AVEC_ID);
        recycleView = (RecyclerView) findViewById(R.id.mfba_rv_display_eleve);
        // L’adapter que l’on souhaite afficher
        recycleView.setAdapter(rVAdapter);
        // Réglage : Est ce qu'on affiche ligne par ligne ou
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        // Réglage : type d’animation qu’on utilise
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddEleve) {
            EleveBean unEleveBean = new EleveBean();
            unEleveBean.setNom(getResources().getString(R.string.rv_lastname) + "_" + new Random().nextInt(20));
            unEleveBean.setPrenom(getResources().getString(R.string.rv_firstname) + "_" + new Random().nextInt(20));
            EleveBddManager.insertEleve(unEleveBean);
            desEleveBeen.add(0, unEleveBean);
            Toast.makeText(MyFirstBddActivity.this, getResources().getString(R.string.mfba_btn_add_eleve), Toast.LENGTH_SHORT).show();
            rVAdapter.notifyDataSetChanged();
        }
        if (v == btnExportBdd) {
            verifyStoragePermissions(this);
            MaBaseSqlite.CopySQLiteBaseToDownload(this, "myFirstBdd.db");
        }
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                                             );
        }
    }

    @Override
    public void onEleveClic(EleveBean eleveBean) {
        EleveBddManager.removeEleveWithID(eleveBean.getId());
        rVAdapter.notifyItemRemoved(desEleveBeen.indexOf(eleveBean));
        desEleveBeen.remove(eleveBean);
    }

    @Override
    public void onLongEleveClic(EleveBean eleveBean) {

    }
}
