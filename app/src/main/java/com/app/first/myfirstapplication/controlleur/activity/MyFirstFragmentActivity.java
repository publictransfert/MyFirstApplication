package com.app.first.myfirstapplication.controlleur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.application.MyFirstApplication;
import com.app.first.myfirstapplication.controlleur.fragment.FragmentCallBack;
import com.app.first.myfirstapplication.controlleur.fragment.MyDetailFragment;
import com.app.first.myfirstapplication.controlleur.fragment.MyListFragment;
import com.app.first.myfirstapplication.model.beans.EleveBean;

public class MyFirstFragmentActivity extends AppCompatActivity implements View.OnClickListener, FragmentCallBack {

    private FrameLayout fl_fragment2;
    private MyListFragment listFragment;
    private MyDetailFragment detailFragment = null;
    private FragmentCallBack fragmentCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_fragment);
        //On définit si on utilise 1 ou 2 layout en fonction de l'appareil.
        //le fragment 2
        fl_fragment2 = (FrameLayout) findViewById(R.id.fl_fragment2);

        //Si on souhaite afficher 2 fragments en même temps
        if (MyFirstApplication.getInstance().isTwoPane()) {
            fl_fragment2.setVisibility(View.VISIBLE);
        }
        else {
            fl_fragment2.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //On verifie si les fragments n'existent pas déjà.
        //Ceux ci peuvent avoir été recréés par Android lors d'une rotation d'écran par exemple. On les récupère
        //grâce à leur tag.
        listFragment = (MyListFragment) getSupportFragmentManager().findFragmentByTag(MyListFragment.class.toString());
        detailFragment = (MyDetailFragment) getSupportFragmentManager().findFragmentByTag(MyDetailFragment.class.toString());
        listFragment = new MyListFragment();
        listFragment.setCallBack(this);
        //Si la liste n'existe pas on la crée.
        if (listFragment == null) {
            listFragment = new MyListFragment();
        }
        if (MyFirstApplication.getInstance().isTwoPane()) {
            detailFragment = new MyDetailFragment();
            //on le positionne sur le 2eme emplacement
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment2, detailFragment,
                    MyDetailFragment.class.toString()).commit();
        }
        //on positionne le fragment sur l'emplacement fragment1
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment1, listFragment,
                MyListFragment.class.toString()).commit();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClickOnEleve(EleveBean eleve) {

        if (MyFirstApplication.getInstance().isTwoPane()) {
            detailFragment.setEleve(eleve);
            detailFragment.refreshScreen();
        }
        else {
            //on demande à l’OS de remplacer le fragment
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            detailFragment = new MyDetailFragment();
            detailFragment.setEleve(eleve);
            //ATTENTION A NE PAS MODIFIER L’INTERFACE GRAPHIQUE DU FRAGMENT QUI N’EXISTE PAS ENCORE
            //IL NE SERA CRÉER QUE PLUS TARD QUAND ANDROID LE SOUHAITERA
            ft.replace(R.id.fl_fragment1, detailFragment, MyDetailFragment.class.toString());
            ft.addToBackStack(null); // permet de revenir à l'ecran d'avant avec un back bouton
            ft.commit();
        }
    }
}
