package com.example.trago_driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class BasicRegistrationInfo extends AppCompatActivity {

    EditText enterName,enterPhone,enterEmail;
    AutoCompleteTextView enterCity;
    Button sendOTP;
    private String verificationID;





    private static final String[] CITY = new String[]{
            "Ahmadnagar", "Akola", "Amravati", "Aurangabad", "Bhandara", "Bhusawal", "Bid", "Buldana", "Chandrapur", "Daulatabad", "Dhule",
            "Jalgaon", "Kalyan", "Karli", "Kolhapur", "Mahabaleshwar", "Malegaon", "Matheran", "Mumbai", "Nagpur", "Nanded", "Nashik", "Osmanabad",
            "Pandharpur", "Parbhani", "Pune", "Ratnagiri", "Sangli", "Satara", "Sevagram", "Solapur", "Thane", "Ulhasnagar", "Vasai-Virar",
            "Wardha", "Yavatmal",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_registration_info);
        enterName=findViewById(R.id.et_name);
        enterCity=findViewById(R.id.et_city);
        enterPhone=findViewById(R.id.et_phone);
        sendOTP=findViewById(R.id.optButton);
        enterEmail=findViewById(R.id.et_mail);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, CITY);
        enterCity.setAdapter(adapter);



        sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=enterPhone.getText().toString();
                String city=enterCity.getText().toString();
                String name = enterName.getText().toString();
                String mail=enterEmail.getText().toString();
                String vehicle=getIntent().getStringExtra("vehicle");
                String hold=getIntent().getStringExtra("choose");
                if(number.length()==10 && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(name)){
                    if(hold.equals("0")) {
                        Intent intent=new Intent(BasicRegistrationInfo.this,VerificationID.class);
                        intent.putExtra("phonenumber", "+91" + number);
                        intent.putExtra("name", name);
                        intent.putExtra("city", city);
                        intent.putExtra("mail", mail);
                        intent.putExtra("hold", hold);
                        intent.putExtra("vehicle",vehicle);
                        startActivity(intent);
                        finish();
                    }
                    if(hold.equals("1")) {
                        Intent intent=new Intent(BasicRegistrationInfo.this,VerificationIdFleets.class);
                        intent.putExtra("phonenumber", "+91" + number);
                        intent.putExtra("name", name);
                        intent.putExtra("city", city);
                        intent.putExtra("mail", mail);
                        intent.putExtra("hold", hold);
                        startActivity(intent);
                        finish();
                    }

                }
                else {
                    Toast.makeText(BasicRegistrationInfo.this,"Enter all the details properly",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}