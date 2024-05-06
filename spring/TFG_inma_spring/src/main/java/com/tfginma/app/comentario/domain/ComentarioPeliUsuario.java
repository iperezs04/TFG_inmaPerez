package com.tfginma.app.comentario.domain;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(IDComentarioPeliUsuario.class)

public class ComentarioPeliUsuario {

		@Id 
		@ManyToOne
		private Comentario comentario;
		@Id
		@ManyToOne
		private User usuario;
		@Id
		@ManyToOne
		private PeliculaSerie peliculaSerie;
}
