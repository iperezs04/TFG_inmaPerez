package com.example.tfg_inmaperez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActivityAuth extends AppCompatActivity {

    Button registrar, acceder;
    TextView email, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mFirestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        registrar=findViewById(R.id.btnregistrar);
        acceder=findViewById(R.id.btnAcceder);
        email=findViewById(R.id.edtemail);
        password=findViewById(R.id.edtpassw);

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(ActivityAuth.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

                } else {

                    loginUser(emailUser,passUser);
                }
            }

            private void loginUser(String emailUser, String passUser) {
                mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                         startActivity(new Intent(ActivityAuth.this, MainActivity.class));


                            Toast.makeText(ActivityAuth.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActivityAuth.this, "No hay ningun usuario registrado con estas credenciales.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


       registrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String emailUser = email.getText().toString().trim();
               String passUser = password.getText().toString().trim();

               if (emailUser.isEmpty() && passUser.isEmpty()) {
                   Toast.makeText(ActivityAuth.this, "No está registrado", Toast.LENGTH_SHORT).show();
               } else {
                   registerUser(emailUser, passUser);

               }
           }


           private void registerUser(String emailUser, String passUser) {

               mAuth.createUserWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       String id=mAuth.getCurrentUser().getUid();
                       Map<String, Object> map= new HashMap<>();
                       map.put("id", id);
                       map.put("email",emailUser);
                       map.put("password",passUser);

                       mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {
                            startActivity(new Intent(ActivityAuth.this, MainActivity.class));
                               finish();

                               Toast.makeText(ActivityAuth.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(ActivityAuth.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                           }
                       });
                   }
               }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(ActivityAuth.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });

    }
}