package com.app.first.myfirstapplication.controlleur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.notification.NotificationUtils;

public class MyFirstNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNotify, btnNotifyTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_notification);
        btnNotify = (Button) findViewById(R.id.mfn_btn_notifiction_simple);
        btnNotify.setOnClickListener(this);
        btnNotifyTimer = (Button) findViewById(R.id.mfn_btn_notifiction_timer);
        btnNotifyTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnNotify) {
            NotificationUtils.createInstantNotification(this, "Dring !!!");
        }
        if (v == btnNotifyTimer) {
            NotificationUtils.scheduleNotification(this, "Dring !!!", 5000);
        }
    }
}
