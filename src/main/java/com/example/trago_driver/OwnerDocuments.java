package com.example.trago_driver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.Journal;
public class OwnerDocuments extends AppCompatActivity {
    LinearLayout l1, l2, l3;
    TextView help;
    Button submitDocs1,submitDocs2,submitDocs3;
    private ImageView panCard,rc,lic;
    private final int REQUEST_CALLS = 1;
    private final int  PICK_IMAGE_REQUEST = 1;
    String name;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;
    private int temp;
    private Uri panCardUri,rcUri,licUri;
    private LinearLayout linear2,linear3,linear1;
    private ProgressDialog progressDialog;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_documents);
        storageReference = FirebaseStorage.getInstance().getReference();
        name = getIntent().getStringExtra("name");
        help = findViewById(R.id.help);
        submitDocs1 = findViewById(R.id.finalSubmission1);
        submitDocs2 = findViewById(R.id.finalSubmission2);
        submitDocs3 = findViewById(R.id.finalSubmission3);
        l1 = findViewById(R.id.heading1Details);
        l2 = findViewById(R.id.heading2Details);
        l3 = findViewById(R.id.heading3Details);
        lic = findViewById(R.id.h33);
        panCard = findViewById(R.id.h11);
        rc = findViewById(R.id.h21);
        linear2=findViewById(R.id.heading2);
        linear3=findViewById(R.id.heading3);
        linear1=findViewById(R.id.heading1);
        firebaseUser=mAuth.getCurrentUser();
        final String uid=firebaseUser.getUid();




        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String options[] = {"Call", "Mail"};
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerDocuments.this);
                builder.setTitle("Contact us through");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            makePhoneCall();
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:adarshshahi1009@gmail.com"));
                            startActivity(intent);
                        }
                    }


                });
                builder.create().show();


            }
        });


        panCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 0;
                openFileChooser();
            }
        });


        rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 3;
                openFileChooser();
            }
        });

        lic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 10;
                openFileChooser();
            }
        });



        submitDocs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(OwnerDocuments.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                final StorageReference filepath = storageReference.child(name+"_"+uid).child("pan_card_" + Timestamp.now().getSeconds());
                filepath.putFile(panCardUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                Journal journal = new Journal();
                                journal.setImageUrl(imageUrl);
                                journal.setTimeAdded(new Timestamp(new Date()));
                                Map<String, Object> note = new HashMap<>();
                                note.put("passbook", imageUrl);
                                db.collection("DriverWithVehicle").document(name).update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressDialog.dismiss();
                                        linear2.setVisibility(View.VISIBLE);
                                        submitDocs2.setVisibility(View.VISIBLE);
                                        linear1.setVisibility(View.GONE);
                                        submitDocs1.setVisibility(View.GONE);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();

                                    }
                                });


                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                });
            }


        });





        submitDocs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(OwnerDocuments.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final StorageReference filepath = storageReference.child(name+"_"+uid).child("rc_" + Timestamp.now().getSeconds());
                filepath.putFile(rcUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                Journal journal = new Journal();
                                journal.setImageUrl(imageUrl);
                                journal.setTimeAdded(new Timestamp(new Date()));
                                Map<String, Object> note = new HashMap<>();
                                note.put("rc", imageUrl);
                                db.collection("DriverWithVehicle").document(name).update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressDialog.dismiss();
                                        linear3.setVisibility(View.VISIBLE);
                                        submitDocs3.setVisibility(View.VISIBLE);
                                        linear2.setVisibility(View.GONE);
                                        submitDocs2.setVisibility(View.GONE);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();

                    }
                });
            }
        });

        submitDocs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(OwnerDocuments.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final StorageReference filepath = storageReference.child(name+"_"+uid).child("license_" + Timestamp.now().getSeconds());
                filepath.putFile(licUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                Journal journal = new Journal();
                                journal.setImageUrl(imageUrl);
                                journal.setTimeAdded(new Timestamp(new Date()));
                                Map<String, Object> note = new HashMap<>();
                                note.put("license", imageUrl);
                                note.put("time", new Timestamp(new Date()));
                                db.collection("DriverWithVehicle").document(name).update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressDialog.dismiss();
                                       startActivity(new Intent(OwnerDocuments.this,Fleet24hours.class));
                                       finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();

                    }
                });
            }


        });


}


    //Image Loading

    private void openFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode == RESULT_OK ){

                panCardUri=data.getData();
                rcUri=data.getData();
                licUri=data.getData();

             if(temp==0) {
                 Picasso.with(this).load(panCardUri).into(panCard);
             }
             else if(temp==3){
                 Picasso.with(this).load(rcUri).into(rc);
             }
             else if(temp==10){
                 Picasso.with(this).load(licUri).into(lic);
             }
            }
                }
    private void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(OwnerDocuments.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(OwnerDocuments.this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALLS);
        }
        else {
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:8055415105")));
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CALLS){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else {
                Toast.makeText(OwnerDocuments.this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        progressDialog.dismiss();
    }
}

