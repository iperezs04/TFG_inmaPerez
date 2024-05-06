package com.tfginma.app.comentario.domain;

import java.io.Serializable;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor

public class IDComentarioPeliUsuario implements Serializable{

	private Comentario comentario;
	private User usuario;
	private PeliculaSerie peliculaSerie;
}
