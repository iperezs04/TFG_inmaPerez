package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Infrastructure.ApiClient;

import com.example.tfg_inmaperez.Infrastructure.PeliService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements  MyRecyclerVIewAdapter.ItemClickListener{

    Button btnPelis, btnSeries;
    BottomNavigationView bottomNavi;
    private PeliService peliculaSerieApi;
    RecyclerView recyclerView;
    List<Peliseri> listaPeliserie;

    MyRecyclerVIewAdapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavi = findViewById(R.id.bottomNavigationView);
        btnPelis = findViewById(R.id.buttonPelis);
        btnSeries = findViewById(R.id.buttonSeries);


        listaPeliserie= new ArrayList<>();

        myadapter= new MyRecyclerVIewAdapter(getApplicationContext(), listaPeliserie);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(myadapter);
        myadapter.setClickListener(this);


        //menuNavi y opciones
        bottomNavi.setOnItemSelectedListener(item ->
        {

            if (item.getItemId() == R.id.peliculaserie) {
                Intent intentpeliculaserie = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intentpeliculaserie);
                finish();
            }
            if (item.getItemId() == R.id.buscar) {
                Intent intentbuscar = new Intent(getApplicationContext(), ActivityBuscar.class);

                startActivity(intentbuscar);
                finish();
            }
            if (item.getItemId() == R.id.perfil) {
                Intent intentperfil = new Intent(getApplicationContext(), ActivityPerfil.class);
                startActivity(intentperfil);

            }
            return true;

        });

        peliculaSerieApi = ApiClient.getClient().create(PeliService.class);

        fetchPeliculaSerie();
    }

   private void fetchPeliculaSerie() {
        Call<List<Peliseri>> call = peliculaSerieApi.getAllPeliculaSerie();
        call.enqueue(new Callback<List<Peliseri>>() {
            @Override
            public void onResponse(Call<List<Peliseri>> call, Response<List<Peliseri>> response) {
                if (response.isSuccessful()) {
                    List<Peliseri> peliculasSeries = response.body();
                    if (peliculasSeries != null && !peliculasSeries.isEmpty()) {
                        Peliseri primeraPeliculaSerie = peliculasSeries.get(0);
                        String titulo = primeraPeliculaSerie.getTitulo();
                        Toast.makeText(MainActivity.this, "Título: " + titulo, Toast.LENGTH_LONG).show();


                        listaPeliserie.clear();
                        listaPeliserie.addAll(peliculasSeries);
                        myadapter.notifyDataSetChanged();

                    } else {

                        Toast.makeText(MainActivity.this, "No se encontraron películas o series", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.i("pruebita",response.code()+"");

                    Toast.makeText(MainActivity.this, "Error en la respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Peliseri>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo en la solicitud: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(View activista, int position) {
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
    }
}
