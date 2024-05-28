package com.example.tfg_inmaperez.Infrastructure;

import com.example.tfg_inmaperez.Application.ValoracionRequest;
import com.example.tfg_inmaperez.Domain.Valoracion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ValoracionService {

        @POST("publicar")
        Call<Valoracion> publicarValoracion(@Body ValoracionRequest valoracionRequest);

        @GET("media/{id}")
        Call<Float> getValoracionMedia(@Path("id") Long id);
}
