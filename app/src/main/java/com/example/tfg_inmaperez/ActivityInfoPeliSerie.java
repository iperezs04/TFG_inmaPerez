package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Infrastructure.ApiClient;
import com.example.tfg_inmaperez.Infrastructure.PeliService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class ActivityInfoPeliSerie extends AppCompatActivity {
    TextView titulo, sinopsis, genero;
    ImageView imagenPeli;
    private Long id;
    Button atras, resenia;
    List<Peliseri> listaPeliserie;
    private PeliService peliculaSerieApi;
    private Peliseri pelicula;
    String emailrecogido;

    RatingBar ratinEstrellitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_peli_serie);
        titulo = findViewById(R.id.tvtitulo);
        sinopsis = findViewById(R.id.editTextTextMultiLine);
        imagenPeli = findViewById(R.id.imageView);
        genero = findViewById(R.id.edtGenero);
        resenia = findViewById(R.id.btnResenia);
        ratinEstrellitas = findViewById(R.id.ratingBar);




        Intent intent= getIntent();
        id=intent.getLongExtra("id", 0);
        pelicula=new Peliseri();

        emailrecogido=intent.getStringExtra("email");

        atras = findViewById(R.id.btnatrasMAA);
        listaPeliserie= new ArrayList<>();
        peliculaSerieApi = ApiClient.getClient().create(PeliService.class);

        fetchPeliculaSerie();

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAtras = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intentAtras);
                finish();

            }
        });

        resenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResenia= new Intent(getApplicationContext(), ActivityValorar.class);

                intentResenia.putExtra("usuario", emailrecogido);
                intentResenia.putExtra("pelicula", pelicula.getIdPeliculaSerie());

                startActivity(intentResenia);
               finish();

            }
        });

    }

    public Peliseri getPeli(Long id, List<Peliseri> lista) {

        Peliseri peliRetorno=null;

        for (Peliseri peli: lista) {
            if(peli.getIdPeliculaSerie() == id){
                peliRetorno=peli;
            }

        }

        return peliRetorno;
    }
    public void setDatos (Peliseri peli){
        titulo.setText(peli.getTitulo());
        sinopsis.setText(peli.getSinopsis());
        imagenPeli.setImageBitmap(peli.getBmp());
        genero.setText(peli.getGenero());
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

                        Toast.makeText(ActivityInfoPeliSerie.this, "No se encontraron películas o series", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.i("pruebita",response.code()+"");

                    Toast.makeText(ActivityInfoPeliSerie.this, "Error en la respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Peliseri>> call, Throwable t) {
                Toast.makeText(ActivityInfoPeliSerie.this, "Fallo en la solicitud: " + t.getMessage(), Toast.LENGTH_LONG).show();
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

                        pelicula= getPeli(id ,lisPeliSeri);
                        setDatos(pelicula);

                      //  myadapter.notifyDataSetChanged();

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

}