package com.app.first.myfirstapplication.controlleur.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.first.myfirstapplication.R;

import java.util.Calendar;

public class MyFirstMenuAndDialogBoxActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextView txtTime, txtDate;
    private static final int FINE_LOCATION_REQ_CODE = 1;

    //Remplir le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_first_menu_and_dialog_box, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Intercepter un click sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_time_picker:
                Calendar calendarTime = Calendar.getInstance();
                //(Context, callback, heure, minute, 24h format)
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.menu_date_picker:
                //Gestion de la date
                Calendar calendar = Calendar.getInstance();
                //Création de la fenêtre
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                //Afficher la fenêtre
                datePickerDialog.show();
                break;
            case R.id.menu_alert_dialog:
                //Préparation de la fenêtre
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                //Message
                alertDialogBuilder.setMessage("My message");
                //Titre
                alertDialogBuilder.setTitle("My title");
                //Bouton ok
                alertDialogBuilder.setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Affiche un toast apres le click sur le bouton ok
                                Toast.makeText(MyFirstMenuAndDialogBoxActivity.this, "Click on ok",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Icone
                alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                //Afficher la fenêtre
                alertDialogBuilder.show();
                break;
            case R.id.menu_recycleview:
                Intent intentMyFirstRecycleViewActivity = new Intent(this, MyFirstRecycleViewActivity.class);
                startActivity(intentMyFirstRecycleViewActivity);
                break;
            case R.id.menu_okhttp_webclient:
                Intent intentMyFirstOkHttpAndWebClientActivity = new Intent(this, MyFirstOkHttpAndWebClientActivity.class);
                startActivity(intentMyFirstOkHttpAndWebClientActivity);
                break;

            case R.id.menu_service_permission:

                //Etape 1 : Est ce qu'on a déjà la permission ?
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //On a la permission
                    Intent intentMyFirstServiceAndPermissionActivity = new Intent(this, MyFirstServiceAndPermissionActivity.class);
                    startActivity(intentMyFirstServiceAndPermissionActivity);
                }
                else {
                    //Etape 2 : On demande la permission
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQ_CODE);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_first_menu_and_dialog_box);
        Log.v("LOGS_APPLICATIF", "ON_CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("LOGS_APPLICATIF", "ON_START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("LOGS_APPLICATIF", "ON_RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("LOGS_APPLICATIF", "ON_PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("LOGS_APPLICATIF", "ON_STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("LOGS_APPLICATIF", "ON_DESTROY");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hourOfDayUpdated;
        String minuteUpdated;
        if (hourOfDay < 10) {
            hourOfDayUpdated = "0" + hourOfDay;
        }
        else {
            hourOfDayUpdated = "" + hourOfDay;
        }
        if (minute < 10) {
            minuteUpdated = "0" + minute;
        }
        else {
            minuteUpdated = "" + minute;
        }

        Toast.makeText(MyFirstMenuAndDialogBoxActivity.this, hourOfDayUpdated + ":" + minuteUpdated, Toast.LENGTH_SHORT).show();
        txtTime = (TextView) findViewById(R.id.madb_tv_time);
        txtTime.setText(hourOfDayUpdated + ":" + minuteUpdated, EditText.BufferType.EDITABLE);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(MyFirstMenuAndDialogBoxActivity.this, dayOfMonth + "/" + (monthOfYear + 1) + "/" + year, Toast.LENGTH_SHORT).show();
        txtDate = (TextView) findViewById(R.id.madb_tv_date);
        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year, EditText.BufferType.EDITABLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Est ce que c'est la permission qu'on a demandé ?
        if (requestCode == FINE_LOCATION_REQ_CODE) {
            //On verifie la réponse
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                Intent intentMyFirstServiceAndPermissionActivity = new Intent(this, MyFirstServiceAndPermissionActivity.class);
                startActivity(intentMyFirstServiceAndPermissionActivity);
            }
            else {
                //On n'a pas la permission
                Toast.makeText(this, "Authorization required", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
