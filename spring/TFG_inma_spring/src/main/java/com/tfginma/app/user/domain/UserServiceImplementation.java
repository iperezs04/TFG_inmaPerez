package com.tfginma.app.user.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
@Service
public class UserServiceImplementation implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	
	public UserServiceImplementation(IUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Optional<List<PeliculaSerie>> getPelisSeriesFav(Long id) {
		return userRepository.findById(id)
				.map(usuario->{
					return usuario.getPeliculaSerieFavorita();
				});
	}
}
