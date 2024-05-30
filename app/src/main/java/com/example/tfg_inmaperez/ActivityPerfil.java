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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Infrastructure.ApiClient;
import com.example.tfg_inmaperez.Infrastructure.PeliService;
import com.example.tfg_inmaperez.Infrastructure.UserApiClient;
import com.example.tfg_inmaperez.Infrastructure.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPerfil extends AppCompatActivity implements  MyRecyclerVIewAdapter1.ItemClickListener {

    Button atrass, cerrarsesion;
    TextView nombre;
    MyRecyclerVIewAdapter1 myadapter;
    RecyclerView recyclerView;
    String emailRecogido;
    private PeliService peliculaSerieApi;
    List<Peliseri> listaPeliserie, listaPeliFavs;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        imagen = findViewById(R.id.imageViewperfil);
        imagen.setImageResource(R.mipmap.cineicono_foreground);
        atrass = findViewById(R.id.btnatrasMA1);
        nombre = findViewById(R.id.txtnombre);
        cerrarsesion = findViewById(R.id.btncerrarsesion);

        listaPeliserie = new ArrayList<>();
        listaPeliFavs= new ArrayList<>();







        myadapter = new MyRecyclerVIewAdapter1(getApplicationContext(), listaPeliserie);
        recyclerView = findViewById(R.id.rv2);
        // Configurar el RecyclerView con un GridLayoutManager de 2 columnas
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myadapter);
        myadapter.setClickListener(this);







        atrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAtras = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intentAtras);
                finish();

            }
        });
        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signOut();
            }
        });

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

            String email = currentUser.getEmail();
            if (email != null) {

                String username = email.split("@")[0];


                if (username.length() > 0) {
                    username = username.substring(0, 1).toUpperCase() + username.substring(1);
                }

                nombre.setText(username);

            }
        }

    peliculaSerieApi = ApiClient.getClient().create(PeliService .class);

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

                        Toast.makeText(ActivityPerfil.this, "No se encontraron películas o series", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.i("pruebita",response.code()+"");

                    Toast.makeText(ActivityPerfil.this, "Error en la respuesta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Peliseri>> call, Throwable t) {
                Toast.makeText(ActivityPerfil.this, "Fallo en la solicitud: " + t.getMessage(), Toast.LENGTH_LONG).show();
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

                        UserService userService = UserApiClient.getClient().create(UserService.class);
                        FirebaseUser currentUserEmail = mAuth.getCurrentUser();
                        Call<List<Long>> callDos = userService.getPelisSeriesFav(currentUserEmail.getEmail());


                        callDos.enqueue(new Callback<List<Long>>() {
                            @Override
                            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    List<Long> peliculasFavoritas = response.body();
                                        listaPeliserie.clear();
                                       listaPeliserie= peliFav(peliculasFavoritas,lisPeliSeri);
                                       myadapter.notifyDataSetChanged();
                                    Log.d("peliculas", "hay peliculas pero no la vemos");
                                } else {
                                   // Toast.makeText(ActivityPerfil.this, "no hay pelis fav", Toast.LENGTH_SHORT).show();
                                Log.d("peliculas", "no hay peliculas");
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Long>> call, Throwable t) {
                                Toast.makeText(ActivityPerfil.this, "error", Toast.LENGTH_SHORT).show();
                                Log.d("peliculas", "las liao");
                            }
                        });

                       //  listaPeliserie.addAll(lisPeliSeri);
                       // myadapter.notifyDataSetChanged();
                       // setPeliculasFavoritas(lisPeliSeri);

                    } else {
                        Toast.makeText(ActivityPerfil.this, "error buscando peliS", Toast.LENGTH_SHORT).show();
                        Log.d("peliculas", "no se que pasa");
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
       // Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(activista.getContext(),ActivityInfoPeliSerie.class );
        intent.putExtra("id", myadapter.getItem(position).getIdPeliculaSerie());

        intent.putExtra("email", emailRecogido);


        startActivity(intent);
        finish();
    }
    private void navigateToLoginScreen() {
        // Código para navegar a la pantalla de inicio de sesión

        Intent intAuth= new Intent( ActivityPerfil.this, ActivityAuth.class);

        startActivity(intAuth);
        finish();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        navigateToLoginScreen();
        finish();
    }

    public List<Peliseri> peliFav(List<Long> listaIds, List<Peliseri>listaPeliculas){

        for (Peliseri elem: listaPeliculas) {

            if(listaIds.contains(elem.getIdPeliculaSerie())){
                listaPeliserie.add(elem);
            }
        }

        return listaPeliserie;
    }
    public void setPeliculasFavoritas(List<Peliseri> listaTodasPeliculas){
        listaPeliserie= listaTodasPeliculas;
    }
}