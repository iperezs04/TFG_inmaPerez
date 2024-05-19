package com.example.tfg_inmaperez.Infrastructure;

import com.example.tfg_inmaperez.Application.ValoracionRequest;
import com.example.tfg_inmaperez.Domain.Valoracion;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ValoracionService {

        @POST("publicar")
        Call<Valoracion> publicarValoracion(@Body ValoracionRequest valoracionRequest);

}
