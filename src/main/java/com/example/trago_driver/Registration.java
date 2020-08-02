package com.example.trago_driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Registration extends AppCompatActivity {

    CardView adarsh,harshal;
    public String hold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        adarsh=findViewById(R.id.CardView);
        harshal=findViewById(R.id.CardView1);


        adarsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,ChooseActivity.class);
                startActivity(intent);

            }
        });

        harshal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hold="1";
                Intent intent=new Intent(Registration.this,BasicRegistrationInfo.class);
                intent.putExtra("choose",hold);
                startActivity(intent);
            }
        });


    }
}