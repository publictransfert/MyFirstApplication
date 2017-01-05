package com.app.first.myfirstapplication.vue.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.model.beans.EleveBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyFirstRecycleViewAdapter extends RecyclerView.Adapter<MyFirstRecycleViewAdapter.ViewHolder> {

    public enum TYPE_AFFICHAGE {AVEC_ID, AVEC_IMAGE}

    private ArrayList<EleveBean> eleveBeanList;
    private RVCallBack callBack;
    private boolean showID = false;
    private TYPE_AFFICHAGE type_affichage;

    //Constructeur
    public MyFirstRecycleViewAdapter(ArrayList<EleveBean> eleveBeanList, RVCallBack callBack, TYPE_AFFICHAGE type_affichage) {
        this.eleveBeanList = eleveBeanList;
        this.callBack = callBack;
        this.type_affichage = type_affichage;
    }

    //Classe qui stocke les composants graphiques d'1 ligne
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPrenom, txtNom, txtId;
        public ImageView imgEleve;
        public LinearLayout layoutCellule;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.mfbrv_txt_id);
            txtPrenom = (TextView) itemView.findViewById(R.id.mfbrv_txt_firstname);
            txtNom = (TextView) itemView.findViewById(R.id.mfbrv_txt_lastname);
            layoutCellule = (LinearLayout) itemView.findViewById(R.id.mfbrv_ll_cellule_eleve);
            imgEleve = (ImageView) itemView.findViewById(R.id.mfbrv_img_eleve);
        }
    }

    //Détermine quel fichier XML on utilise pour représanter une cellule
    @Override
    public MyFirstRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {

        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.activity_my_first_bdd_recycle_view, vg, false);
        return new MyFirstRecycleViewAdapter.ViewHolder(v);
    }

    //Remplir les composants graphique de chaque cellule
    @Override
    public void onBindViewHolder(MyFirstRecycleViewAdapter.ViewHolder holder, int position) {
        //L'élève correspondant à la ligne
        final EleveBean eleveBean = eleveBeanList.get(position);
        holder.txtPrenom.setText(eleveBean.getPrenom());
        holder.txtNom.setText(eleveBean.getNom());
        if (type_affichage == TYPE_AFFICHAGE.AVEC_IMAGE) {
            holder.imgEleve.setVisibility(View.VISIBLE);
            holder.txtId.setVisibility(View.GONE);
            Glide.with(holder.imgEleve.getContext()).load(eleveBean.getPhoto()).asBitmap().into(holder.imgEleve);
        }
        else {
            holder.txtId.setVisibility(View.VISIBLE);
            holder.imgEleve.setVisibility(View.GONE);
            String id = "" + eleveBean.getId();
            holder.txtId.setText(id);
        }

        holder.layoutCellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onEleveClic(eleveBean);
                }
            }
        });
        holder.layoutCellule.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (callBack != null) {
                    callBack.onLongEleveClic(eleveBean);
                }
                return false;
            }
        });
    }

    //Combien de cellule on affiche
    @Override
    public int getItemCount() {
        return eleveBeanList.size();
    }

    public interface RVCallBack {
        void onEleveClic(EleveBean eleveBean);

        void onLongEleveClic(EleveBean eleveBean);
    }
}
