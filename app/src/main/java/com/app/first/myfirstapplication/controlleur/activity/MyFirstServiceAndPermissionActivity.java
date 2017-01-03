package com.app.first.myfirstapplication.controlleur.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.first.myfirstapplication.controlleur.application.MyFirstApplication;
import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.service.MyFirstService;
import com.squareup.otto.Subscribe;

public class MyFirstServiceAndPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart, btnStop;
    private TextView tvLatAndLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_service_and_permission);
        btnStart = (Button) findViewById(R.id.mfsapa_btn_start);
        btnStop = (Button) findViewById(R.id.mfsapa_btn_stop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        tvLatAndLong = (TextView) findViewById(R.id.mfsapa_tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyFirstApplication.getEventBus().register(this);
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

    @Subscribe
    public void afficherLatAndLong(Location location) {
        tvLatAndLong.setText(location.getLatitude() + " : " + location.getLongitude());
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyFirstApplication.getEventBus().unregister(this);
    }
}
