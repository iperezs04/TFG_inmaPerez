package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityInfoPeliSerie extends AppCompatActivity {
TextView  titulo;
Button atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_peli_serie);
        titulo=findViewById(R.id.tvtitulo);


        atras=findViewById(R.id.btnatrasMAA);


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAtras = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intentAtras);
                finish();

            }
        });

    }
}