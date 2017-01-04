package com.app.first.myfirstapplication.controlleur.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.at.ICallBack;
import com.app.first.myfirstapplication.controlleur.at.LoadEleveAT;
import com.app.first.myfirstapplication.model.beans.EleveBean;
import com.app.first.myfirstapplication.model.dao.web.WebService;
import com.app.first.myfirstapplication.vue.adapter.MyFirstRecycleViewAdapter;

import java.util.ArrayList;
import java.util.Random;

public class MyFirstRecycleViewActivity extends AppCompatActivity implements View.OnClickListener, MyFirstRecycleViewAdapter.RVCallBack, ICallBack {

    private Button btnAddEleve, btnLoadEleve, btnLoadEleveAT;
    // Données
    private boolean showID = false;
    private ArrayList<EleveBean> desEleveBeen;
    private MyFirstRecycleViewAdapter rVAdapter;
    // Composant graphique afficheur de RecyclerView.Adapter
    private RecyclerView recycleView;
    private MyFirstRecycleViewAdapter.RVCallBack callback;

    private final String EXTRA_LIST_ELEVE = "mes_eleves";

    private LoadEleveAT loadEleveAT = null;

    ProgressDialog myFirstProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_recycle_view);
        btnAddEleve = (Button) findViewById(R.id.rv_btn_add);
        btnAddEleve.setOnClickListener(this);
        btnLoadEleve = (Button) findViewById(R.id.rv_btn_load);
        btnLoadEleve.setOnClickListener(this);
        btnLoadEleveAT = (Button) findViewById(R.id.rv_btn_load_asynctask);
        btnLoadEleveAT.setOnClickListener(this);
        // Création de la liste
        desEleveBeen = new ArrayList<EleveBean>();
        // Instanciation d’un MyFirstRecycleViewAdapter
        rVAdapter = new MyFirstRecycleViewAdapter(desEleveBeen, this, MyFirstRecycleViewAdapter.TYPE_AFFICHAGE.AVEC_IMAGE);
        recycleView = (RecyclerView) findViewById(R.id.rv_rv_eleve);
        // L’adapter que l’on souhaite afficher
        recycleView.setAdapter(rVAdapter);
        // Réglage : Est ce qu'on affiche ligne par ligne ou
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        //recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        //recycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //new GridLayoutManager(this, 2) //Sous forme de tableau à 2 colonnes
        // Réglage : type d’animation qu’on utilise
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddEleve) {
            EleveBean unEleveBean = new EleveBean();
            unEleveBean.setNom(getResources().getString(R.string.rv_lastname) + "_" + new Random().nextInt(20));
            unEleveBean.setPrenom(getResources().getString(R.string.rv_firstname) + "_" + new Random().nextInt(20));
            desEleveBeen.add(0, unEleveBean);
            Toast.makeText(MyFirstRecycleViewActivity.this, getResources().getString(R.string.rv_add), Toast.LENGTH_SHORT).show();
            rVAdapter.notifyDataSetChanged();
        }
        else if (v == btnLoadEleve) {
            ArrayList<EleveBean> temp = WebService.loadEleveFromWeb();
            desEleveBeen.clear();
            if (temp != null) {
                desEleveBeen.addAll(temp);
            }
            Toast.makeText(MyFirstRecycleViewActivity.this, getResources().getString(R.string.rv_load), Toast.LENGTH_SHORT).show();
            rVAdapter.notifyDataSetChanged();
        }
        else if (v == btnLoadEleveAT) {
            Toast.makeText(MyFirstRecycleViewActivity.this, getResources().getString(R.string.rv_load), Toast.LENGTH_SHORT).show();

            // le if permet de ne pas lancer plusieurs chargement de thread en parrallèle
            if (loadEleveAT == null || loadEleveAT.getStatus() == AsyncTask.Status.FINISHED) {
                loadEleveAT = new LoadEleveAT(this);
                loadEleveAT.execute();
            }
            myFirstProgressDialog = ProgressDialog.show(MyFirstRecycleViewActivity.this, getResources().getString(R.string.rv_pd_title), getResources().getString(R.string
                    .rv_pd_message), true, false);
        }
    }

    @Override
    public void onEleveClic(EleveBean eleveBean) {
        Toast.makeText(MyFirstRecycleViewActivity.this, eleveBean.getNom() + " " + getResources().getString(R.string.rv_delete), Toast.LENGTH_SHORT).show();
        rVAdapter.notifyItemRemoved(desEleveBeen.indexOf(eleveBean));
        desEleveBeen.remove(eleveBean);
    }

    @Override
    public void onLongEleveClic(EleveBean eleveBean) {
        if (desEleveBeen.indexOf(eleveBean) == 0) {
            Toast.makeText(MyFirstRecycleViewActivity.this, getResources().getString(R.string.rv_already_first), Toast.LENGTH_SHORT).show();
        }
        else {
            EleveBean eleveBeanNouvellePosition = eleveBean;
            rVAdapter.notifyItemMoved(desEleveBeen.indexOf(eleveBeanNouvellePosition), 0);
            desEleveBeen.remove(eleveBean);
            desEleveBeen.add(0, eleveBeanNouvellePosition);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_LIST_ELEVE, desEleveBeen);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            //On passe par une variable intermediaire, sinon le cast ne passe pas.
            ArrayList<EleveBean> temp = savedInstanceState.getParcelableArrayList(EXTRA_LIST_ELEVE);
            desEleveBeen.clear();
            if (temp != null) {
                desEleveBeen.addAll(temp);
            }
        }
    }

    @Override
    public void onEleveLoad(ArrayList<EleveBean> mesEleveBeen) {
        desEleveBeen.clear();
        if (mesEleveBeen != null) {
            desEleveBeen.addAll(mesEleveBeen);
        }
        if (myFirstProgressDialog != null) {
            myFirstProgressDialog.dismiss();
        }
        rVAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myFirstProgressDialog != null) {
            myFirstProgressDialog.dismiss();
        }
    }
}