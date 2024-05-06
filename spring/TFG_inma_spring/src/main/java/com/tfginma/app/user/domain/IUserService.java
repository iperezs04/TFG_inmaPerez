package com.tfginma.app.user.domain;

import java.util.List;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;

public interface IUserService {

	public List<PeliculaSerie> getPelisSeriesFav();
	
	public List<User> getSeguidos();
	
	public List<User> getSeguidores();
	
	
}
