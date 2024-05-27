package com.example.tfg_inmaperez.Domain;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.tfg_inmaperez.R;
import com.google.firebase.firestore.auth.User;

import java.io.Serializable;
import java.util.List;

public class Peliseri implements Serializable {

    private Long idPeliculaSerie;
    private String titulo;
    private String tipo;
    private String genero;
    private String temporada;
    private String sinopsis;
    private String imagen;
    private float valoracionMedia;

    private List<Valoracion> listaValoracion;

    public List<Valoracion> getListaValoracion() {
        return listaValoracion;
    }

    public void setListaValoracion(List<Valoracion> listaValoracion) {
        this.listaValoracion = listaValoracion;
    }

    Bitmap bmp;

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public Long getIdPeliculaSerie() {
        return idPeliculaSerie;
    }

    public void setIdPeliculaSerie(Long idPeliculaSerie) {
        this.idPeliculaSerie = idPeliculaSerie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getValoracionMedia() {
        return valoracionMedia;
    }

    public void setValoracionMedia(float valoracionMedia) {
        this.valoracionMedia = valoracionMedia;
    }

}