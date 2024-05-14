package com.tfginma.app.peliculaserie.domain;

import java.util.List;
import java.util.Optional;

public interface IPeliculaSerieService {

	public Optional<List<PeliculaSerie>> getPeliculaSerie();
	
	public Optional<List<PeliculaSerie>> getPeliculaSeriePorTipo(Tipo tipo);
	
	public Optional<List<PeliculaSerie>> getPeliculaSeriePorGenero(Genero genero);
	
	public Optional<PeliculaSerie> addPeliSerie(PeliculaSerie peliculaSerie);
	
	public Optional<PeliculaSerie> deletePeliSerie(Long id);
	
	public Optional<PeliculaSerie> modifyPeliSerie(Long id, PeliculaSerie peliculaSerie);
}
