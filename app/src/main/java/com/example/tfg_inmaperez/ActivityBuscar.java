package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Infrastructure.ApiClient;
import com.example.tfg_inmaperez.Infrastructure.PeliService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBuscar extends AppCompatActivity implements  MyRecyclerView1.ItemClickListener{

    Button atras;
    private PeliService peliculaSerieApi;
    RecyclerView recyclerView;
    List<Peliseri> listaPeliserie;

    MyRecyclerView1 myadapter;
    private List<Peliseri> pelisfiltradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        atras=findViewById(R.id.btnparaMA);



        listaPeliserie= new ArrayList<>();
        peliculaSerieApi = ApiClient.getClient().create(PeliService.class);
        fetchPeliculaSerie();

        myadapter= new MyRecyclerView1(getApplicationContext(), listaPeliserie);
        recyclerView = findViewById(R.id.recyclerv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(myadapter);
        myadapter.setClickListener(this);



        SearchView searchView = findViewById(R.id.searchvw);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               filtrar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrar(newText);
                return false;
            }
        });


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAtras = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intentAtras);
                finish();

            }
        });


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

                        peliculasSeries.forEach(peliseri -> {
                            fetchImagefromURL(peliculasSeries, peliseri);
                        });


                    } else {

                        Toast.makeText(ActivityBuscar.this, "No se encontraron películas o series", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.i("pruebita",response.code()+"");

                    Toast.makeText(ActivityBuscar.this, "Error en la respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Peliseri>> call, Throwable t) {
                Toast.makeText(ActivityBuscar.this, "Fallo en la solicitud: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchImagefromURL( List<Peliseri> lisPeliSeri ,Peliseri peliseri){

        Call<ResponseBody> call = peliculaSerieApi.fetchCaptcha(peliseri.getImagen());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                        peliseri.setBmp(bmp);

                        listaPeliserie.clear();
                        listaPeliserie.addAll(lisPeliSeri);
                        myadapter.notifyDataSetChanged();

                    } else {
                        // TODO
                    }
                } else {
                    fetchImagefromURL(lisPeliSeri, peliseri);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // TODO
            }
        });

    }

    @Override
    public void onItemClick(View activista, int position) {
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
    }

    public void filtrar(String texto) {

        Log.i("filtrar", texto);
        if (!texto.isEmpty()) {
            pelisfiltradas= listaPeliserie.stream().filter(peliseri -> peliseri
                    .getTitulo()
                    .toLowerCase()
                    .contains(texto.toLowerCase())).collect(Collectors.toList());

           /* for (Peliseri pelicula : mData) {
                if (pelicula.getTitulo().toLowerCase().contains(texto) ) {
                    pelisfiltradas.add(pelicula);
                }
            }*/

            myadapter.setLista(pelisfiltradas);
        }else{
            myadapter.setLista(listaPeliserie);
        }


    }

}
