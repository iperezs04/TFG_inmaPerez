package com.tfginma.app.user.domain;

import java.util.List;
import java.util.Optional;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;

public interface IUserService {

	public Optional<List<PeliculaSerie>> getPelisSeriesFav(Long id);
	
//	public Optional<List<User>> getSeguidos(Long id);
//	
//	public Optional<List<User>> getSeguidores(Long id);
	
	
}