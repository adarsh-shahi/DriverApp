package com.example.trago_driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ChooseActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private Button button;
    private CardView cv,cv1,cv2,cv3,cv4,cv5,cv6,cv7,cv8,cv9;
    private FirebaseFirestore db =FirebaseFirestore.getInstance();
    String name,city,mail,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        checkBox=findViewById(R.id.checkBox);
        button=findViewById(R.id.chooseBtn);
        name= getIntent().getStringExtra("name");
        city=getIntent().getStringExtra("city");
        mail=getIntent().getStringExtra("mail");
        phone=getIntent().getStringExtra("phone");
        cv=findViewById(R.id.v0);
        cv1=findViewById(R.id.v1);
        cv2=findViewById(R.id.v2);
        cv3=findViewById(R.id.v3);
        cv4=findViewById(R.id.v4);
        cv5=findViewById(R.id.v5);
        cv6=findViewById(R.id.v6);
        cv7=findViewById(R.id.v7);
        cv8=findViewById(R.id.v8);
        cv9=findViewById(R.id.v9);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("ape");
            }

        });

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("ace");
            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("bularo");
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("407");
            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("truck");
            }
        });

        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("sandtruck");
            }
        });

        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("trailer");
            }
        });

        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("jcb");
            }
        });


        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("crane");
            }
        });

        cv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote("truckplus");
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    button.setVisibility(View.VISIBLE);
                }
                else {
                    button.setVisibility(View.GONE);
                }
            }
        });



    }

    private void saveNote(String vehicle) {


        Intent intent =new Intent(ChooseActivity.this,BasicRegistrationInfo.class);
        intent.putExtra("vehicle",vehicle);
        intent.putExtra("choose","0");
        startActivity(intent);






    }


}