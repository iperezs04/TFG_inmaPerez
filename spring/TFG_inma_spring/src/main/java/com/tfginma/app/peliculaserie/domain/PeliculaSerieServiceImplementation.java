package com.tfginma.app.peliculaserie.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

public class PeliculaSerieServiceImplementation implements IPeliculaSerieService {
	@Autowired
	private IPeliSerieRepository peliSerieRepository;
	
	public PeliculaSerieServiceImplementation(IPeliSerieRepository peliSerieRepository) {
		super();
		this.peliSerieRepository = peliSerieRepository;
	}
	
	@Override
	public Optional<List<PeliculaSerie>> getPeliculaSerie() {
		return Optional.of(peliSerieRepository.findAll());
	}
	
	@Override
	public Optional<List<PeliculaSerie>> getPeliculaSeriePorTipo(Tipo tipo) {		
		return Optional.of(peliSerieRepository
					.findAll()
						.stream()
							.filter(peliSerie->
								peliSerie.getTipo().equals(tipo))
									.collect(Collectors.toList()));
	}
	
	@Override
	public Optional<List<PeliculaSerie>> getPeliculaSeriePorGenero(Genero genero) {		
		return Optional.of(peliSerieRepository
					.findAll()
						.stream()
							.filter(peliSerie->
								peliSerie.getGenero().equals(genero))
									.collect(Collectors.toList()));
	}

	@Override
	public Optional<PeliculaSerie> addPeliSerie(PeliculaSerie peliculaSerie) {
		return Optional.of(peliSerieRepository.save(peliculaSerie));
	}

	@Override
	public Optional<PeliculaSerie> deletePeliSerie(Long id) {	
		return peliSerieRepository.findById(id)
				.map(peliSerie->
						{peliSerieRepository.delete(peliSerie);
							return peliSerie;});
	}

	@Override
	public Optional<PeliculaSerie> modifyPeliSerie(Long id, PeliculaSerie peliculaSerie) {		
		return peliSerieRepository
				.findById(id)
					.map(peliSerie->
							{peliSerie.setDatosModificados(peliculaSerie);
								return peliSerieRepository.save(peliSerie);});
	}

	

}
