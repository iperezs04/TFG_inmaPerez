package com.tfginma.app.valoracion.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

public class ValoracionServiceImplementation implements IValoracionService {
	@Autowired
	private IValoracionRepository valoracionRepository;
	
	public ValoracionServiceImplementation(IValoracionRepository valoracionRepository) {
		super();
		this.valoracionRepository = valoracionRepository;
	}

	@Override
	public Optional<Double> getValoracionMedia() {
		return Optional.of((valoracionRepository
					.findAll()
						.stream()
							.mapToDouble(valoracion->
									valoracion.getValor())
							.average())
				.getAsDouble());
	}

	@Override
	public Optional<Valoracion> publicarValoracion(Valoracion valoracion, User usuario, PeliculaSerie peliculaSerie) {
		valoracion.getValoraciones().add(new ValoracionPeliUsuario(valoracion, usuario, peliculaSerie));
		return Optional.of(valoracionRepository.save(valoracion));
	}

}
