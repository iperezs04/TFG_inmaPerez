package com.example.tfg_inmaperez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tfg_inmaperez.Application.ValoracionRequest;
import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Domain.User;
import com.example.tfg_inmaperez.Domain.Valoracion;
import com.example.tfg_inmaperez.Infrastructure.ApiClient;
import com.example.tfg_inmaperez.Infrastructure.PeliService;
import com.example.tfg_inmaperez.Infrastructure.UserApiClient;
import com.example.tfg_inmaperez.Infrastructure.UserService;
import com.example.tfg_inmaperez.Infrastructure.ValoracionApiClient;
import com.example.tfg_inmaperez.Infrastructure.ValoracionService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityValorar extends AppCompatActivity {

    RatingBar estrellitasValoracion;
    Button btnok;

    String usuario;
    Peliseri peliculaserie;
    Long idPelicula;
    private PeliService peliculaSerieApi;
    List<Peliseri> listaPeliserie;
    RadioButton si, no;
    RadioGroup group;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    Boolean trueFav=false;
    Float valoracion , guardarRating;
    ImageView imagenvalo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valorar);
        estrellitasValoracion = findViewById(R.id.ratingBar3);
        btnok = findViewById(R.id.btnOK);
        si=findViewById(R.id.radioButtonSi);
        no=findViewById(R.id.radioButtonNo);
        group=findViewById(R.id.radiogroup);
        usuario = "";
        valoracion=2f;
        guardarRating=1f;
        peliculaserie = new Peliseri();
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        imagenvalo=findViewById(R.id.imgvalo);
        imagenvalo.setImageResource(R.mipmap.valoracionicon_foreground);

        FirebaseUser currentUser = mAuth.getCurrentUser();


        Intent intent = getIntent();
        usuario= currentUser.getEmail();
        idPelicula = intent.getLongExtra("pelicula", 0);

        UserService apiServiceUser = UserApiClient.getClient().create(UserService.class);
        ValoracionService apiServiceValoracion = ValoracionApiClient.getClient().create(ValoracionService.class);



        ValoracionRequest valoracionRequest = new ValoracionRequest(valoracion, usuario, idPelicula);
        peliculaSerieApi = ApiClient.getClient().create(PeliService.class);


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonSi) {
                    Toast.makeText(ActivityValorar.this, "Película marcada como Favorito", Toast.LENGTH_SHORT).show();
                     trueFav=true;



                } else if (checkedId == R.id.radioButtonNo) {
                    Toast.makeText(ActivityValorar.this, "Película marcada como No Favorito", Toast.LENGTH_SHORT).show();
                    trueFav=false;
                }
            }

        });

        estrellitasValoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                guardarRating= rating;
                Log.d("RatingBar", "Rating changed: " + rating + ", fromUser: " + fromUser);

            }
        });



        btnok.setOnClickListener(v -> {

           valoracion = guardarRating;
           Log.d("rating bar",valoracion.toString());
            Log.d("rating bar 2",String.valueOf(estrellitasValoracion.getRating()) );



            valoracionRequest.setUsuario(usuario);
            valoracionRequest.setPeliserie(idPelicula);
            valoracionRequest.setValoracion(valoracion);
           Call<Valoracion> callValo = apiServiceValoracion.publicarValoracion(valoracionRequest);
            callValo.enqueue(new Callback<Valoracion>() {
                @Override
                public void onResponse(Call<Valoracion> callValo, Response<Valoracion> response) {
                    if (response.isSuccessful()) {
                        Valoracion publicada = response.body();

                        Log.d("Activityvalorar", "Valoración publicada: " + publicada);
                    } else {
                        Log.e("Activityvalorar", "Request fallida, codigo: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Valoracion> callValo, Throwable t) {

                    Log.e("Activityvalorar", "Request fallida", t);
                }
            });

                if(trueFav){


                    String email = currentUser.getEmail();

                    Call<Peliseri> call = apiServiceUser.addPeliFav(email, idPelicula);


                    call.enqueue(new Callback<Peliseri>() {
                        @Override
                        public void onResponse(Call<Peliseri> call, Response<Peliseri> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Log.d("email",currentUser.getEmail());
                            } else {
                                Log.d("emailno",currentUser.getEmail());
                            }
                        }

                        @Override
                        public void onFailure(Call<Peliseri> call, Throwable t) {
                            // Maneja el fallo en la conexión
                        }
                    });
                }else{
                    Toast.makeText(this, "No se añadio a fav", Toast.LENGTH_SHORT).show();
                }

            Intent intentPerfil=new Intent(ActivityValorar.this, ActivityPerfil.class);
            startActivity(intentPerfil);
            finish();
        });

    }
}
