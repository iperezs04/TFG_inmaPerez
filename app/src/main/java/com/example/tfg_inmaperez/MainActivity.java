package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Infrastructure.ApiClient;

import com.example.tfg_inmaperez.Infrastructure.PeliService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements  MyRecyclerVIewAdapter.ItemClickListener{

    Button btnPelis, btnSeries;
    BottomNavigationView bottomNavi;
    private PeliService peliculaSerieApi;
    RecyclerView recyclerView;
    List<Peliseri> listaPeliserie, pelisLista, seriesLista;

    MyRecyclerVIewAdapter myadapter;
    String emailRecogido, tipopeli,tiposerie;
    ImageView imagenicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavi = findViewById(R.id.bottomNavigationView);
        btnPelis = findViewById(R.id.buttonPelis);
        btnSeries = findViewById(R.id.buttonSeries);
       imagenicon=findViewById(R.id.imageView3);
        imagenicon.setImageResource(R.mipmap.claqueta_foreground);
        listaPeliserie= new ArrayList<>();
        pelisLista=new ArrayList<>();
        seriesLista=new ArrayList<>();
        tipopeli="pelicula";
        tiposerie="serie";
        Intent intentemail= getIntent();
                emailRecogido=intentemail.getStringExtra("email");



        myadapter = new MyRecyclerVIewAdapter(getApplicationContext(), listaPeliserie);
        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myadapter);
        myadapter.setClickListener(this);



        btnSeries.setOnClickListener(v -> {
            if (!listaPeliserie.isEmpty()) {
                seriesLista= listaPeliserie.stream().filter(peliseri -> peliseri
                        .getTipo()
                        .toLowerCase()
                        .contains(tiposerie.toLowerCase())).collect(Collectors.toList());



                myadapter.setLista(seriesLista);
            }else{
                myadapter.setLista(listaPeliserie);
            }


        });

        btnPelis.setOnClickListener(v -> {
            if (!listaPeliserie.isEmpty()) {
                pelisLista= listaPeliserie.stream().filter(peliseri -> peliseri
                        .getTipo()
                        .toLowerCase()
                        .contains(tipopeli.toLowerCase())).collect(Collectors.toList());



                myadapter.setLista(pelisLista);
            }else{
                myadapter.setLista(listaPeliserie);
            }



        });

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

                        peliculasSeries.forEach(peliseri -> {
                            fetchImagefromURL(peliculasSeries, peliseri);
                        });


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
           Log.d("fallito",t.getMessage() );
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
        Intent intent= new Intent(activista.getContext(),ActivityInfoPeliSerie.class );
        intent.putExtra("id", myadapter.getItem(position).getIdPeliculaSerie());

        intent.putExtra("email", emailRecogido);


        startActivity(intent);
        finish();
    }

}
