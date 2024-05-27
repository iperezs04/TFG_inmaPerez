package com.example.tfg_inmaperez.Infrastructure;

import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("users/addPelifav/{nombre}/{idPeli}")
    Call<Peliseri> addPeliFav(@Path("nombre") String nombre, @Path("idPeli") Long idPeli);

    @GET("users/{nombre}")
    Call<List<Long>> getPelisSeriesFav(@Path("nombre") String nombre);
    @POST("users/addUser/{emailuser}")
    Call<User> addUser(@Path("emailuser") String emailUser);
}
