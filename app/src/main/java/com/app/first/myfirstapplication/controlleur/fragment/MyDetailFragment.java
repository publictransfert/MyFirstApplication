package com.app.first.myfirstapplication.controlleur.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.model.beans.EleveBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDetailFragment extends Fragment {

    private TextView tv;
    private EleveBean eleve;

    public MyDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        tv = (TextView) rootView.findViewById(R.id.df_tv);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshScreen();
    }

    public void setEleve(EleveBean eleve) {
        this.eleve = eleve;
    }

    public void refreshScreen() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (eleve == null) {
                    tv.setText("Aucun élève");
                }
                else {
                    tv.setText(eleve.getPrenom() + " " + eleve.getNom());
                }
            }
        });
    }
}


