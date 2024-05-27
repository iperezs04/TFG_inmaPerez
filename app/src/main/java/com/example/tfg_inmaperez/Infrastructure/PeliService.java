package com.example.tfg_inmaperez.Infrastructure;

import com.example.tfg_inmaperez.Domain.Peliseri;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.List;

public interface PeliService {
        @GET("peliseries")
        Call<List<Peliseri>> getAllPeliculaSerie();
        @GET
        Call<ResponseBody> fetchCaptcha(@Url String url);



}
