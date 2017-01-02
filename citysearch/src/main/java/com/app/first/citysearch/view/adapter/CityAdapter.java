package com.app.first.citysearch.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.first.citysearch.R;
import com.app.first.citysearch.model.beans.CityBean;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private ArrayList<CityBean> mesVilles;

    //Constructeur
    public CityAdapter(ArrayList<CityBean> mesVilles) {
        this.mesVilles = mesVilles;
    }

    //Classe qui stocke les composants graphiques d'1 ligne
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvResults;
        public LinearLayout llResults;

        public ViewHolder(View itemView) {
            super(itemView);
            tvResults = (TextView) itemView.findViewById(R.id.mfrrvc_tv);
            llResults = (LinearLayout) itemView.findViewById(R.id.mfrrvc_ll);
        }
    }

    //Détermine quel fichier XML on utilise pour représanter une cellule
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.activity_my_first_rest_recycle_view_cell, vg, false);
        return new CityAdapter.ViewHolder(v);
    }

    //Remplir les composants graphique de chaque cellule
    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        final CityBean maVille = mesVilles.get(position);
        holder.tvResults.setText(maVille.getCp() + " : " + maVille.getVille());
    }

    //Combien de cellule on affiche
    @Override
    public int getItemCount() {
        return mesVilles.size();
    }
}
