package com.app.first.citysearch.model.dao.web;

import com.app.first.citysearch.model.beans.ResultBean;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebServiceUtils {

    private static final String URL = "http://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp=";

    public static ResultBean result(String cp) {
        try {
            String response = sendGetOkHttpRequest(URL + cp);
            Gson gson = new Gson();
            ResultBean result = gson.fromJson(response, ResultBean.class);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
