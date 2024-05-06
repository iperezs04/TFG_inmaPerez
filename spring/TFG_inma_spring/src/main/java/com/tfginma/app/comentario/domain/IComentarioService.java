package com.tfginma.app.comentario.domain;

import java.util.List;

public interface IComentarioService {

	public List<Comentario> getComentarios();
	
	public Comentario publicarComentario();
	
	public Comentario borrarComentario();
	
	
}
