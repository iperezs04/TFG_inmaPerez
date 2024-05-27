package com.tfginma.app.user.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements IUserService {
	@Autowired
	private IUserRepository userRepository;

	public UserServiceImplementation(IUserRepository userRepository) {
		super();
		this.userRepository = userRepository;

	}

	@Override
	public Optional<List<Long>> getPelisSeriesFav(String nombre) {
		return userRepository.findByNombre(nombre).map(usuario -> {
			return usuario.getPeliculaSerieFavorita();
		});
	}

	@Override
	public Optional<Long> addPeliFav(String nombre, Long idPeli) {
		User user = new User();

		user = userRepository.findByNombre(nombre).get();
		List<Long> idPeliculas = new ArrayList<>();
		if (user.getPeliculaSerieFavorita() != null) {
			for (Long idPeliElem : user.getPeliculaSerieFavorita()) {
				idPeliculas.add(idPeliElem);
			}
		}
		idPeliculas.add(idPeli);
		user.setPeliculaSerieFavorita(idPeliculas);
		userRepository.save(user);

		return Optional.of(idPeli);
		/*
		 * return userRepository.findAll() .stream() .filter(usuarioBuscado ->
		 * usuarioBuscado.getNombre().equalsIgnoreCase(nombre)) .findFirst()
		 * .map(usuario->{ usuario.setPeliculaSerieFavorita(new ArrayList<Long>());
		 * usuario.getPeliculaSerieFavorita().add(idPeli); userRepository.save(usuario);
		 * return idPeli; });
		 */

	}

	@Override
	public User addUser(String email) {
		List<Long> listaIdPelis = new ArrayList<>();
		return userRepository.save(new User(email, listaIdPelis));
	}
}
