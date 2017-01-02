package com.app.first.myfirstapplication.model.dao.web;

import android.os.SystemClock;

import com.app.first.myfirstapplication.model.beans.EleveBean;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebService {

    public static ArrayList<EleveBean> loadEleveFromWeb() {
        ArrayList<EleveBean> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(new EleveBean("Elève_" + i, "Prénom"));
            SystemClock.sleep(500);
        }
        return temp;
    }

    public static String sendGetOkHttpRequest(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        //Création de la requete
        Request request = new Request.Builder().url(url).build();
        //Execution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() != 200) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        }
        else {
            //Résultat de la requete.
            return response.body().string();
        }
    }
}
