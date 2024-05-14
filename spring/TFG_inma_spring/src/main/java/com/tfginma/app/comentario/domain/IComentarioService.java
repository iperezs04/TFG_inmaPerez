package com.tfginma.app.comentario.domain;

import java.util.List;
import java.util.Optional;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

public interface IComentarioService {

	public Optional<List<Comentario>> getComentariosPeli(Long idPeli);
	
	public Optional<List<Comentario>> getComentariosUsuario(Long id);
	
	public Optional<Comentario> publicarComentario(PeliculaSerie peliSerie, Comentario comentario, User usuario);
	
	public Optional<Comentario> borrarComentario(Long id);
	
	
}
