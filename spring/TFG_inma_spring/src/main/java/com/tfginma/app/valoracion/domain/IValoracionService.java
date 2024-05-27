package com.tfginma.app.valoracion.domain;

import java.util.Optional;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

public interface IValoracionService {

	public Optional<Float> getValoracionMedia(Long id);
	
	public Optional<Valoracion> publicarValoracion(Float valoracion, String usuario, Long peliculaSerie);
	
}
