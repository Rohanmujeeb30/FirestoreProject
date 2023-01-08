package com.example.firestoreproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating your account");
        progressDialog.setMessage("Please wait");
        EditText editText = findViewById(R.id.name);
        EditText editText1 = findViewById(R.id.email);
        EditText editText2 = findViewById(R.id.password);
        EditText editText3 = findViewById(R.id.phone);
        EditText editText4 = findViewById(R.id.age);
        Button btn = findViewById(R.id.button);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString();
                String email = editText1.getText().toString();
                String password = editText2.getText().toString();
                String phone = editText3.getText().toString();
                String age = editText4.getText().toString();

                if (!name.equalsIgnoreCase("")&&!password.equalsIgnoreCase("")
                && !email.equalsIgnoreCase("")&& !phone.equalsIgnoreCase("")&& !age.equalsIgnoreCase("")){
                    progressDialog.show();
                    HashMap user = new HashMap();
                    user.put("Name",name);
                    user.put("Email",email);
                    user.put("Password",password);
                    user.put("Phone Number",phone);
                    user.put("Age",age);

                    db.collection("Rohaan users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

                    Toast.makeText(getApplicationContext(),"Please enter the details first",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}