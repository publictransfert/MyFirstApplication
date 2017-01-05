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
import com.app.first.myfirstapplication.model.dao.bdd.EleveGreenDaoManager;
import com.app.first.myfirstapplication.model.dao.bdd.MaBaseSqlite;
import com.app.first.myfirstapplication.vue.adapter.MyFirstRecycleViewForGreenDaoAdapter;

import java.util.ArrayList;
import java.util.Random;

import javapackage.Eleve;

public class MyFirstGreenDaoActivity extends AppCompatActivity implements View.OnClickListener, MyFirstRecycleViewForGreenDaoAdapter.RVCallBack {

    private Button btnAddEleve, btnExportBdd;
    // Données
    private ArrayList<Eleve> desEleves;
    private MyFirstRecycleViewForGreenDaoAdapter rVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_green_dao);
        btnAddEleve = (Button) findViewById(R.id.mfgd_btn_add_eleve);
        btnAddEleve.setOnClickListener(this);
        btnExportBdd = (Button) findViewById(R.id.mfgd_btn_export);
        btnExportBdd.setOnClickListener(this);
        desEleves = new ArrayList<>();
        desEleves.addAll(EleveGreenDaoManager.getAllEleve());
        rVAdapter = new MyFirstRecycleViewForGreenDaoAdapter(desEleves, this);
        RecyclerView recycleView = (RecyclerView) findViewById(R.id.mfgd_rv_display_eleve);
        recycleView.setAdapter(rVAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddEleve) {
            Eleve unEleve = new Eleve();
            unEleve.setNom(getResources().getString(R.string.rv_lastname) + "_" + new Random().nextInt(20));
            unEleve.setPrenom(getResources().getString(R.string.rv_firstname) + "_" + new Random().nextInt(20));
            EleveGreenDaoManager.insertOrUpdate(unEleve);
            desEleves.add(0, unEleve);
            Toast.makeText(MyFirstGreenDaoActivity.this, getResources().getString(R.string.btn_add_eleve), Toast.LENGTH_SHORT).show();
            rVAdapter.notifyDataSetChanged();
        }
        if (v == btnExportBdd) {
            verifyStoragePermissions(this);
            MaBaseSqlite.CopySQLiteBaseToDownload(this, "mygreentable-db");
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
    public void onEleveClic(Eleve unEleve) {
        EleveGreenDaoManager.deleteEleveWithId(unEleve.getId());
        rVAdapter.notifyItemRemoved(desEleves.indexOf(unEleve));
        desEleves.remove(unEleve);
    }

    @Override
    public void onLongEleveClic(Eleve unEleve) {

    }
}
