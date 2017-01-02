package com.app.first.citysearch.controller.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.first.citysearch.R;
import com.app.first.citysearch.model.beans.CityBean;
import com.app.first.citysearch.model.beans.ResultBean;
import com.app.first.citysearch.model.dao.web.WebServiceUtils;
import com.app.first.citysearch.view.adapter.CityAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MyFirstRestActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCodePostal;
    private Button btSearch;
    private ArrayList<CityBean> mesVilles;
    private CityAdapter rVAdapter;
    private RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_rest);
        etCodePostal = (EditText) findViewById(R.id.mfr_et_search);
        btSearch = (Button) findViewById(R.id.mfr_bt_search);
        btSearch.setOnClickListener(this);
        mesVilles = new ArrayList<>();
        rVAdapter = new CityAdapter(mesVilles);
        recycleView = (RecyclerView) findViewById(R.id.mfr_rv_results);
        recycleView.setAdapter(rVAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onClick(View v) {
        if (v == btSearch) {
            String codePostal = etCodePostal.getText().toString();
            LoadURL loadURL = new LoadURL(codePostal);
            loadURL.execute();
        }
    }

    public class LoadURL extends AsyncTask {

        private String codePostal;

        public LoadURL(String codePostal) {
            this.codePostal = codePostal;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                ResultBean response = WebServiceUtils.result(codePostal);
                return response;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // UIThread
        protected void onPostExecute(Object object) {
            mesVilles.clear();
            ResultBean result = (ResultBean) object;
            mesVilles.addAll(Arrays.asList(result.getResults()));
            rVAdapter.notifyDataSetChanged();
        }
    }
}
