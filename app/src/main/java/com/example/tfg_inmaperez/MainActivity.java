package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Button btnPelis, btnSeries;
    BottomNavigationView bottomNavi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavi=findViewById(R.id.bottomNavigationView);
        btnPelis= findViewById(R.id.buttonPelis);
        btnSeries=findViewById(R.id.buttonSeries);


        //menuNavi y opciones
        bottomNavi.setOnItemSelectedListener(item ->
        {

            if(item.getItemId()==R.id.peliculaserie) {
                Intent intentpeliculaserie = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intentpeliculaserie);
                finish();
            } if (item.getItemId()==R.id.buscar) {
            Intent intentbuscar = new Intent(getApplicationContext(), ActivityBuscar.class);

            startActivity(intentbuscar);
            finish();
        }if (item.getItemId()==R.id.perfil){
            Intent intentperfil = new Intent(getApplicationContext(), ActivityPerfil.class);
            startActivity(intentperfil);

        }
            return true;

        });

    }
}