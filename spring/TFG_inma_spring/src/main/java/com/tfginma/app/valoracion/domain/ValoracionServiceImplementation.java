package com.tfginma.app.valoracion.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfginma.app.peliculaserie.domain.IPeliSerieRepository;
import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.IUserRepository;
import com.tfginma.app.user.domain.User;

@Service
public class ValoracionServiceImplementation implements IValoracionService {
	@Autowired
	private IValoracionRepository valoracionRepository;
	@Autowired
	private IPeliSerieRepository peliserieRepository;
	@Autowired
	IUserRepository userRepository;



	public ValoracionServiceImplementation(IValoracionRepository valoracionRepository,
			IPeliSerieRepository peliserieRepository, IUserRepository userRepository) {
		super();
		this.valoracionRepository = valoracionRepository;
		this.peliserieRepository = peliserieRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Optional<Float> getValoracionMedia(Long id) {
		return valoracionRepository
				/*	.findAll()
						.stream().
							flatMap(valoracion-> 
								valoracion.getValoraciones()
									.stream())
										.filter(valoracionesPeliSeries->
											valoracionesPeliSeries.getPeliculaSerie().getIdPeliculaSerie()==id)
										.collect(Collectors.toList())
									.stream()
										.mapToDouble(valores->
											valores.getValoracion().getValor())
							
									.average()
								    .stream()
								    .mapToObj(avg -> (float) avg) 
								    .findFirst();					*/
		
		 .findAll()
         .stream()
         .flatMap(valoracion -> valoracion.getValoraciones().stream())
         .filter(valoracionPeliUsuario -> valoracionPeliUsuario.getPeliculaSerie().getIdPeliculaSerie().equals(id))
         .mapToDouble(valoracionPeliUsuario -> valoracionPeliUsuario.getValoracion().getValor())
         .average()
         .stream()
         .mapToObj(avg -> (float) avg) 
         .findFirst();
	}

	@Override
	public Optional<Valoracion> publicarValoracion(Float valoracion, String usuario, Long peliculaSerie) {
		// valoracion.getValoraciones().add(new ValoracionPeliUsuario(valoracion,
		// usuario, peliculaSerie));
		// return Optional.of(valoracionRepository.save(valoracion));

		Valoracion valoracio = new Valoracion();
		List<ValoracionPeliUsuario> listaValoracionestabla = new ArrayList<>();
		valoracio.setValor(valoracion);
		listaValoracionestabla.add(new ValoracionPeliUsuario(valoracio, userRepository.findByNombre(usuario).get(), peliserieRepository.findById(peliculaSerie).get()));
		valoracio.setValoraciones(listaValoracionestabla);
		valoracionRepository.save(valoracio);


		return Optional.of(valoracio);
	}

}
