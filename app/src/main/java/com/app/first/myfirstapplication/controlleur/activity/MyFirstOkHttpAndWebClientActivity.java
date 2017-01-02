package com.app.first.myfirstapplication.controlleur.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.model.dao.web.WebService;

public class MyFirstOkHttpAndWebClientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUrl;
    private Button btnLoad;

    private WebView wvUrlContent;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_ok_http_and_web_client);
        btnLoad = (Button) findViewById(R.id.ohawc_btn_load_url);
        etUrl = (EditText) findViewById(R.id.ohawc_et_url);
        wvUrlContent = (WebView) findViewById(R.id.ohawc_web);
        // Sinon cela lance le navigateur du téléphone
        wvUrlContent.setWebViewClient(new WebViewClient());
        WebSettings webviewSettings = wvUrlContent.getSettings();
        webviewSettings.setJavaScriptEnabled(true);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnLoad) {
            String url = etUrl.getText().toString();
            wvUrlContent.loadUrl(url);
            LoadURL loadURL = new LoadURL(url);
            loadURL.execute();
        }
    }

    public class LoadURL extends AsyncTask {
        private String url;

        public LoadURL(String url) {
            this.url = url;
        }

        // Garantie en dehors de l’UIThread
        @Override
        protected Object doInBackground(Object[] params) {
            // traitement
            String response = null;
            try {
                response = WebService.sendGetOkHttpRequest(url);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        // UIThread
        protected void onPostExecute(Object object) {
            String responseContent = (String) object;
            TextView tvUrlContent;
            tvUrlContent = (TextView) findViewById(R.id.ohawc_tv);
            tvUrlContent.setText(responseContent);
        }
    }
}

