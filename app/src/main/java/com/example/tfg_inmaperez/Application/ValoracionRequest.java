package com.example.tfg_inmaperez.Application;

import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Domain.Valoracion;
import com.google.firebase.firestore.auth.User;

public class ValoracionRequest {

    private Valoracion valoracion;
    private User usuario;
    private Peliseri peliserie;

    public ValoracionRequest(Valoracion valoracion, User usuario, Peliseri peliserie) {
        this.valoracion = valoracion;
        this.usuario = usuario;
        this.peliserie = peliserie;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Peliseri getPeliserie() {
        return peliserie;
    }

    public void setPeliserie(Peliseri peliserie) {
        this.peliserie = peliserie;
    }
}




