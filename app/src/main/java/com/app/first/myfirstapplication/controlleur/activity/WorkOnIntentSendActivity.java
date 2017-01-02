package com.app.first.myfirstapplication.controlleur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.first.myfirstapplication.R;

public class WorkOnIntentSendActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText myTextEdit;
    private Button btnSend;
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_on_intent_send);

        myTextEdit = (EditText) findViewById(R.id.wois_txt_edit);

        btnSend = (Button) findViewById(R.id.wois_btn_send);
        btnSend.setOnClickListener(this);

        myTextView = (TextView) findViewById(R.id.wois_txt_view);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WorkOnIntentReceiveActivity.class);
        intent.putExtra("text", myTextEdit.getText().toString());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        myTextView.setText(data.getStringExtra("reply"));
    }
}
;