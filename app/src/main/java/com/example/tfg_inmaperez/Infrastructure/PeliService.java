package com.example.tfg_inmaperez.Infrastructure;

import com.example.tfg_inmaperez.Domain.Peliseri;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PeliService {
        @GET("peliseries")
        Call<List<Peliseri>> getAllPeliculaSerie();
}
