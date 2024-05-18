package com.example.tfg_inmaperez.Domain;

public class Valoracion {
    private Long idValoracion;


    private Integer valor;
    private  Long idSerie;
    private Long idUser;


    public Long getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
