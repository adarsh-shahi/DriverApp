package com.example.trago_driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser currentUserId;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        currentUserId=mAuth.getCurrentUser();
    }



    @Override
    protected void onStart() {
        super.onStart();
        if(currentUserId==null){
            startActivity(new Intent(MainActivity.this, FirstScreen.class));
            finish();
        }
        else {
            startActivity(new Intent(MainActivity.this,OwnerDocuments.class));
            finish();
        }
    }
}