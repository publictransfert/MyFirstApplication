package com.app.first.myfirstapplication.controlleur.at;

import android.os.AsyncTask;
import android.util.Pair;

import com.app.first.myfirstapplication.model.beans.EleveBean;
import com.app.first.myfirstapplication.model.dao.web.WebService;

import java.util.ArrayList;

public class LoadEleveAT extends AsyncTask<Void, Pair<Integer, Integer>, ArrayList<EleveBean>> {

    ICallBack callBack;

    public LoadEleveAT() {
    }

    public LoadEleveAT(ICallBack callBack) {
        this.callBack = callBack;
    }

    // Garantie en dehors de l’UIThread
    @Override
    protected ArrayList<EleveBean> doInBackground(Void... params) {
        // traitement
        ArrayList<EleveBean> temp = WebService.loadEleveFromWeb();
        return temp;
    }

    // UIThread
    @Override
    protected void onPostExecute(ArrayList<EleveBean> eleveBeanList) {
        super.onPostExecute(eleveBeanList);
        callBack.onEleveLoad(eleveBeanList);
    }

    @Override
    protected void onProgressUpdate(Pair<Integer, Integer>... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
