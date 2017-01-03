package com.app.first.myfirstapplication.controlleur.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.app.first.myfirstapplication.R;
import com.app.first.myfirstapplication.controlleur.activity.MyFirstHelloWorldActivity;
import com.app.first.myfirstapplication.controlleur.activity.MyFirstNotificationActivity;
import com.app.first.myfirstapplication.controlleur.broadcast.MyFirstNotificationBroadcastReceiver;

/**
 * Created by hb on 03/01/2017.
 */
public class NotificationUtils {

    //Envoyer une notification immediate
    public static void createInstantNotification(Context context, String message) {
        //Ce qui se passera quand on cliquera sur la notif
        Intent intent = new Intent(context, MyFirstNotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent,
                PendingIntent.FLAG_ONE_SHOT);
        //La notification
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Le titre")
                .setContentText(message)
                .setContentIntent(pendingIntent).build();
        //Envoyer la notification
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        //ENVOIE
        notificationManager.notify(1, notification);
    }

    public static void scheduleNotification(Context context, String message, long delay) {
        //La notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent helloWorldIntent = new Intent(context, MyFirstHelloWorldActivity.class);
        PendingIntent pendingHelloWorldIntent = PendingIntent.getActivity(context, 1, helloWorldIntent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Hello World", pendingHelloWorldIntent).build();
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(message);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.addAction(action);
        //Redirection vers le broadcast
        Intent notificationIntent = new Intent(context, MyFirstNotificationBroadcastReceiver.class);
        notificationIntent.putExtra(MyFirstNotificationBroadcastReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyFirstNotificationBroadcastReceiver.NOTIFICATION, builder.build());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //La dans le futur
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}
