package com.app.first.myfirstapplication.vue.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.first.myfirstapplication.R;

import java.util.ArrayList;

import javapackage.Eleve;

public class MyFirstRecycleViewForGreenDaoAdapter extends RecyclerView.Adapter<MyFirstRecycleViewForGreenDaoAdapter.ViewHolder> {

    private ArrayList<Eleve> desEleves;
    private RVCallBack callBack;
    private boolean showID = false;

    public MyFirstRecycleViewForGreenDaoAdapter(ArrayList<Eleve> desEleves, RVCallBack callBack) {
        this.desEleves = desEleves;
        this.callBack = callBack;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPrenom, txtNom, txtId;
        public LinearLayout layoutCellule;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.mfgdrv_txt_id);
            txtPrenom = (TextView) itemView.findViewById(R.id.mfgdrv_txt_firstname);
            txtNom = (TextView) itemView.findViewById(R.id.mfgdrv_txt_lastname);
            layoutCellule = (LinearLayout) itemView.findViewById(R.id.mfgdrv_ll_cellule_eleve);
        }
    }

    @Override
    public MyFirstRecycleViewForGreenDaoAdapter.ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {

        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.activity_my_first_green_dao_recycle_view, vg, false);
        return new MyFirstRecycleViewForGreenDaoAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyFirstRecycleViewForGreenDaoAdapter.ViewHolder holder, int position) {
        //L'élève correspondant à la ligne
        final Eleve unEleve = desEleves.get(position);
        holder.txtPrenom.setText(unEleve.getPrenom());
        holder.txtNom.setText(unEleve.getNom());
        holder.txtId.setText("" + unEleve.getId());
        holder.txtId.setVisibility(View.VISIBLE);

        holder.layoutCellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onEleveClic(unEleve);
                }
            }
        });

        holder.layoutCellule.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (callBack != null) {
                    callBack.onLongEleveClic(unEleve);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return desEleves.size();
    }

    public interface RVCallBack {
        void onEleveClic(Eleve unEleve);

        void onLongEleveClic(Eleve unEleve);
    }
}
