package com.example.firestoreproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firestoreproject.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Signin extends AppCompatActivity {
ActivitySigninBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating your account");
        progressDialog.setMessage("Please wait");

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!binding.NAME.getText().toString().equalsIgnoreCase("")&&!binding.EMAIL.getText().toString().equalsIgnoreCase("")
                        && !binding.PASSWORD.getText().toString().equalsIgnoreCase("")&& !binding.PHONE.getText().toString().equalsIgnoreCase("")&& !binding.AGE.getText().toString().equalsIgnoreCase("")){
                    progressDialog.show();
                    HashMap user = new HashMap();
                    user.put("NAME", binding.NAME.getText().toString());
                    user.put("EMAIL ADDRESS",binding.EMAIL.getText().toString());
                    user.put("PASSWORD",binding.PASSWORD.getText().toString());
                    user.put("Phone Number", binding.PHONE.getText().toString());
                    user.put("AGE", binding.AGE.getText().toString());

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Sucessfully Register", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(getApplicationContext(),"Please enter the details",Toast.LENGTH_SHORT).show();

                }



            }
        });
    }
}