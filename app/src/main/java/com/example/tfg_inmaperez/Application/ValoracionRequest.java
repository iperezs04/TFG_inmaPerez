package com.example.tfg_inmaperez.Application;

import com.example.tfg_inmaperez.Domain.Peliseri;
import com.example.tfg_inmaperez.Domain.User;
import com.example.tfg_inmaperez.Domain.Valoracion;


public class ValoracionRequest {

    private Float valoracion;
    private String usuario;
    private Long peliserie;

    public ValoracionRequest(Float valoracion, String usuario, Long peliserie) {
        this.valoracion = valoracion;
        this.usuario = usuario;
        this.peliserie = peliserie;
    }

    public Float getValoracion() {
        return valoracion;
    }

    public void setValoracion(Float valoracion) {
        this.valoracion = valoracion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getPeliserie() {
        return peliserie;
    }

    public void setPeliserie(Long peliserie) {
        this.peliserie = peliserie;
    }
}




