package com.example.trago_driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerificationID extends AppCompatActivity {
    private String verificationId;
    EditText enterOTP;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private ProgressBar progressBar;
    String name;
    String city;
    String phoneNumber;
    String hold,mail,vehicle;
    TextView tv;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_i_d);
        tv=findViewById(R.id.check2);

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        progressBar=findViewById(R.id.progressBar);
        name= getIntent().getStringExtra("name");
    city = getIntent().getStringExtra("city");
    hold=getIntent().getStringExtra("hold");
    vehicle=getIntent().getStringExtra("vehicle");
      phoneNumber = getIntent().getStringExtra("phonenumber");
      mail=getIntent().getStringExtra("mail");

        sendVerificationCode(phoneNumber);
        tv.setText(name);
        enterOTP=findViewById(R.id.et_otp);


        findViewById(R.id.buttonOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = enterOTP.getText().toString().trim();
                if(code.isEmpty() || code.length()<6){
                    enterOTP.setError("Enter code...");
                    enterOTP.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredential(credential);


    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                        Toast.makeText(VerificationID.this,hold+" this is ",Toast.LENGTH_LONG).show();
                        savingToDb1();
                        Intent intent = new Intent(VerificationID.this,OwnerDocuments.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.putExtra("name",name);
                        startActivity(intent);
                        finish();
                }
                else {
                    Toast.makeText(VerificationID.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallBack);
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
           if(code!=null){
               progressBar.setVisibility(View.VISIBLE);
               verifyCode(code);
           }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerificationID.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };


    private void savingToDb1(){
      //  String uid=currentUser.getUid();
        Map<String,Object> note = new HashMap<>();
        note.put("phone:",phoneNumber);
        note.put("city",city);
        note.put("name",name);
        note.put("mail",mail);
        note.put("vehicle",vehicle);
     //   note.put("uid",uid);
            db.collection("DriverWithVehicle").document(name).set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(VerificationID.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(VerificationID.this, "Error", Toast.LENGTH_LONG).show();
                }
            });
        }
        }