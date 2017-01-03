package com.app.first.myfirstapplication.controlleur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.first.myfirstapplication.R;

public class MyFirstIntentReceiveActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText myTextEdit;
    private Button btnReply;
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_intent_receive);

        myTextEdit = (EditText) findViewById(R.id.woir_txt_edit);

        btnReply = (Button) findViewById(R.id.woir_btn_reply);
        btnReply.setOnClickListener(this);

        myTextView = (TextView) findViewById(R.id.woir_txt_view);
        myTextView.setText(getIntent().getStringExtra("text"));
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        result.putExtra("reply", myTextEdit.getText().toString());
        setResult(RESULT_OK, result);
        finish();
    }
}
