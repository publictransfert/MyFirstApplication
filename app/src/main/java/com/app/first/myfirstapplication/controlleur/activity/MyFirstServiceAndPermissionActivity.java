package com.app.first.myfirstapplication.controlleur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.service.MyFirstService;

public class MyFirstServiceAndPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_service_and_permission);
        btnStart = (Button) findViewById(R.id.mfsapa_btn_start);
        btnStop = (Button) findViewById(R.id.mfsapa_btn_stop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnStart) {
            //Démarre le service
            startService(new Intent(this, MyFirstService.class));
        }
        else if (v == btnStop) {
            // Stoppe le service
            stopService(new Intent(this, MyFirstService.class));
        }
    }
}
