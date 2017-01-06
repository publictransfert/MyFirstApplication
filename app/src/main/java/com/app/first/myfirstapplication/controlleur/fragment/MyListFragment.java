package com.app.first.myfirstapplication.controlleur.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.model.beans.EleveBean;
import com.app.first.myfirstapplication.vue.adapter.MyFirstRecycleViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment implements MyFirstRecycleViewAdapter.RVCallBack {

    private ArrayList<EleveBean> eleveList;

    private MyFirstRecycleViewAdapter rVAdapter;
    private RecyclerView rv;
    private LinearLayout ll;

    public MyListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_my_first_recycle_view, container, false);

        ll = (LinearLayout) rootView.findViewById(R.id.rv_ll_btns);
        ll.setVisibility(View.GONE);

        if (eleveList == null) {
            eleveList = new ArrayList<>();
            EleveBean eleve1 = new EleveBean();
            eleve1.setNom("Snow");
            eleve1.setPrenom("John");
            eleveList.add(eleve1);
            EleveBean eleve2 = new EleveBean();
            eleve2.setNom("Hunter");
            eleve2.setPrenom("Rick");
            eleveList.add(eleve2);
            EleveBean eleve3 = new EleveBean();
            eleve3.setNom("HunterX");
            eleve3.setPrenom("RickX");
            eleveList.add(eleve3);
        }

        rVAdapter = new MyFirstRecycleViewAdapter(eleveList, this, MyFirstRecycleViewAdapter.TYPE_AFFICHAGE.AVEC_IMAGE);
        rv = (RecyclerView) rootView.findViewById(R.id.rv_rv_eleve);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(rVAdapter);
        rVAdapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onEleveClic(EleveBean eleveBean) {
        if (callBack != null) {
            callBack.onClickOnEleve(eleveBean);
        }
    }

    @Override
    public void onLongEleveClic(EleveBean eleveBean) {

    }

    private FragmentCallBack callBack = null;

    public void setCallBack(FragmentCallBack cb) {
        callBack = cb;
    }
}
