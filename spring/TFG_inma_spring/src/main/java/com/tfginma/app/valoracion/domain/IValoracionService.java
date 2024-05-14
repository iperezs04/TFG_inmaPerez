package com.tfginma.app.valoracion.domain;

import java.util.Optional;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

public interface IValoracionService {

	public Optional<Double> getValoracionMedia();
	
	public Optional<Valoracion> publicarValoracion(Valoracion valoracion, User usuario, PeliculaSerie peliculaSerie);
	
}
