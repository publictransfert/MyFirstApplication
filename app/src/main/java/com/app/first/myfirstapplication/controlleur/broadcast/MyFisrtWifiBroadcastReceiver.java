package com.app.first.myfirstapplication.controlleur.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class MyFisrtWifiBroadcastReceiver extends BroadcastReceiver {
    public MyFisrtWifiBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())
                && WifiManager.WIFI_STATE_ENABLED == wifiState) {
            Toast.makeText(context, "Wifi Enabled!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Wifi Disabled!", Toast.LENGTH_SHORT).show();
        }
    }
}
