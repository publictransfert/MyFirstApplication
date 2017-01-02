package com.app.first.myfirstapplication.controlleur.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyFirstAirPlaneBroadcastReceiver extends BroadcastReceiver {
    public MyFirstAirPlaneBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        if (isAirplaneModeOn) {
            Toast.makeText(context, "Airplane Mode On", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Airplane Mode Off", Toast.LENGTH_SHORT).show();
        }
    }
}
