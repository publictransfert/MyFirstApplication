package com.app.first.myfirstapplication.controlleur.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.first.myfirstapplication.R;

public class MyFirstViewRelativeActivity extends AppCompatActivity implements View.OnClickListener {

    //Déclaration d'un pointeur vers un Button
    private Button btnCancel, btnValidate, btnNext;
    private RadioGroup rbtnGroup;
    private RadioButton rbtnLike, rbtnDontLike;
    private EditText txtEdit;
    private ImageView imgUpdate;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Chargement de l'interface graphique
        setContentView(R.layout.activity_my_first_view_relative_layout);
        //On récupère le Button qui a été créé par le fichier XML
        btnCancel = (Button) findViewById(R.id.vrl_btn_cancel);
        btnValidate = (Button) findViewById(R.id.vrl_btn_validate);
        btnNext = (Button) findViewById(R.id.vrl_btn_next);
        rbtnGroup = (RadioGroup) findViewById(R.id.vrl_rbtn_group);
        rbtnLike = (RadioButton) findViewById(R.id.vrl_rbtn_like);
        rbtnDontLike = (RadioButton) findViewById(R.id.vrl_rbtn_dont_like);
        txtEdit = (EditText) findViewById(R.id.vrl_txt_edit);
        imgUpdate = (ImageView) findViewById(R.id.vrl_img_display);
        btnCancel.setOnClickListener(this);
        btnValidate.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        imgUpdate.setOnClickListener(this);
        Log.v("LOGS_APPLICATIF_HB_MAIN", "ON_CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("LOGS_APPLICATIF_HB_MAIN", "ON_START");
    }

    @Override
    protected void onResume() {
        super.onResume();
        imgUpdate.setColorFilter(Color.TRANSPARENT);
        imgUpdate.setImageResource(R.mipmap.ic_launcher);
        Log.v("LOGS_APPLICATIF_HB_MAIN", "ON_RESUME");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("LOGS_APPLICATIF_HB_MAIN", "ON_PAUSE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("LOGS_APPLICATIF_HB_MAIN", "ON_STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("LOGS_APPLICATIF_HB_MAIN", "ON_DESTROY");
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel) {
            txtEdit.setText(String.valueOf(""));
            rbtnGroup.clearCheck();
            imgUpdate.setImageResource(R.drawable.ic_delete_forever_black_48dp);
            imgUpdate.setColorFilter(Color.RED);
        }
        else if (v == btnValidate) {
            imgUpdate.setImageResource(R.drawable.ic_done_black_48dp);
            imgUpdate.setColorFilter(Color.GREEN);
            if (rbtnGroup.getCheckedRadioButtonId() == rbtnLike.getId()) {
                txtEdit.setText(rbtnLike.getText());
            }
            else if (rbtnGroup.getCheckedRadioButtonId() == rbtnDontLike.getId()) {
                txtEdit.setText(rbtnDontLike.getText());
            }
        }
        else if (v == btnNext) {
            Intent intent = new Intent(this, MyFirstMenuAndDialogBoxActivity.class);
            startActivity(intent);
        }
        else if (v == imgUpdate) {
            imgUpdate.setColorFilter(Color.TRANSPARENT);
            imgUpdate.setImageResource(R.mipmap.ic_launcher);
        }
    }
}