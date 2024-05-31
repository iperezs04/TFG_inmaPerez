package com.example.tfg_inmaperez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg_inmaperez.Domain.User;
import com.example.tfg_inmaperez.Infrastructure.UserApiClient;
import com.example.tfg_inmaperez.Infrastructure.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAuth extends AppCompatActivity {

    Button registrar, acceder;
    TextView email, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    String emailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        registrar = findViewById(R.id.btnregistrar);
        acceder = findViewById(R.id.btnAcceder);
        email = findViewById(R.id.edtemail);
        password = findViewById(R.id.edtpassw);




        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(ActivityAuth.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

                } else {

                    loginUser(emailUser, passUser);
                }
            }

            private void loginUser(String emailUser, String passUser) {
                mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();

                            Intent intentLogin = new Intent(ActivityAuth.this, MainActivity.class);
                            intentLogin.putExtra("email", emailUser);

                            startActivity(intentLogin);
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
                 emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(ActivityAuth.this, "Error. Complete los campos", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(emailUser, passUser);

                }
            }


            private void registerUser(String emailUser, String passUser) {

                mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id = mAuth.getCurrentUser().getUid();
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", id);
                        map.put("email", emailUser);
                        map.put("password", passUser);

                        UserService apiServiceUser = UserApiClient.getClient().create(UserService.class);
                        Call<User> call = apiServiceUser.addUser(emailUser);
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.isSuccessful()) {
                                    User user = response.body();
                                    Toast.makeText(ActivityAuth.this, "Usuario añadido correctamente", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ActivityAuth.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(ActivityAuth.this, "Fallo: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                        mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ActivityAuth.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();


                                startActivity(new Intent(ActivityAuth.this, MainActivity.class));
                                finish();


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

    @Override
    public void onStart() {
        super.onStart();
        // Verificar si el usuario está autenticado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // El usuario ya está autenticado, ir a la pantalla principal
            navigateToMainScreen();
        } else {
            // El usuario no está autenticado, ir a la pantalla de inicio de sesión
            navigateToLoginScreen();
        }
    }

    private void navigateToMainScreen() {
        // Código para navegar a la pantalla principal
        Intent intentLogin = new Intent(ActivityAuth.this, MainActivity.class);
        intentLogin.putExtra("email", emailUser);

        startActivity(intentLogin);

    }

    private void navigateToLoginScreen() {
        // Código para navegar a la pantalla de inicio de sesión
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        FirebaseUser user = mAuth.getCurrentUser();
                        navigateToMainScreen();
                    } else {
                        // Error en el inicio de sesión
                        Toast.makeText(ActivityAuth.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        navigateToLoginScreen();
    }

}