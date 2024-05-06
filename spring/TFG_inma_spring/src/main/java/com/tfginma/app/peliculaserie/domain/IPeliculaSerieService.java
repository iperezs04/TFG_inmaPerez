package com.tfginma.app.peliculaserie.domain;

import java.util.List;

public interface IPeliculaSerieService {

	
	public List<PeliculaSerie> getPeliculaSerie();
	
	public PeliculaSerie addPeliSerie();
	
	public PeliculaSerie deletePeliSerie();
	
	public PeliculaSerie modifyPeliSerie();
}
