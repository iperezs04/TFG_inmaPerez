package com.tfginma.app.valoracion.domain;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;
@Service
public class ValoracionServiceImplementation implements IValoracionService {
	@Autowired
	private IValoracionRepository valoracionRepository;
	
	public ValoracionServiceImplementation(IValoracionRepository valoracionRepository) {
		super();
		this.valoracionRepository = valoracionRepository;
	}

	@Override
	public Optional<Double> getValoracionMedia(Long id) {
		return Optional.of(valoracionRepository
					.findAll()
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
						.getAsDouble());								
	}

	@Override
	public Optional<Valoracion> publicarValoracion(Valoracion valoracion, User usuario, PeliculaSerie peliculaSerie) {
		valoracion.getValoraciones().add(new ValoracionPeliUsuario(valoracion, usuario, peliculaSerie));
		return Optional.of(valoracionRepository.save(valoracion));
	}

}
