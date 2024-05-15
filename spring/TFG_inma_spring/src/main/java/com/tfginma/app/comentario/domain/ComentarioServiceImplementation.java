package com.tfginma.app.comentario.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;
@Service
public class ComentarioServiceImplementation implements IComentarioService {
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public ComentarioServiceImplementation(ComentarioRepository comentarioRepository) {
		super();
		this.comentarioRepository = comentarioRepository;
	}

	@Override
	public Optional<List<Comentario>> getComentariosPeli(Long idPeli) {
		return Optional.of(comentarioRepository
				.findAll()
					.stream()
						.filter(comentario->
									comentario.getComentarios()
										.stream()
											.anyMatch(comentarioPeliUsuario->
													comentarioPeliUsuario.getPeliculaSerie().getIdPeliculaSerie()==idPeli))
						.collect(Collectors.toList()));
	}

	@Override
	public Optional<List<Comentario>> getComentariosUsuario(Long id) {
		return Optional.of(comentarioRepository
				.findAll()
					.stream()
						.filter(comentario->
									comentario.getComentarios()
										.stream()
											.anyMatch(comentarioPeliUsuario->
													comentarioPeliUsuario.getUsuario().getIdUser()==id))
						.collect(Collectors.toList()));
	}

	@Override
	public Optional<Comentario> publicarComentario(PeliculaSerie peliSerie, Comentario comentario, User usuario) {
		//probar
		comentario.getComentarios().add(new ComentarioPeliUsuario(comentario,usuario,peliSerie));
		return Optional.of(comentarioRepository.save(comentario));
	}

	@Override
	public Optional<Comentario> borrarComentario(Long id) {
		return comentarioRepository
				.findById(id)
					.map(comentario->
						{comentarioRepository.delete(comentario);
							return comentario;});
	}


}
