package com.example.trago_driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NeedHelp extends AppCompatActivity {

    EditText probEt;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_help);
        probEt=findViewById(R.id.prob);
        sendBtn=findViewById(R.id.sendBtn);





        final String message=probEt.getText().toString().trim();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(message)){
                    Toast.makeText(NeedHelp.this,"Please Enter Your Message",Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:adarshshahi1009@gmail.com" ));
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Issue related documents");
                    intent.putExtra(Intent.EXTRA_TEXT,message);
                    startActivity(intent);





                }
            }
        });


    }
}