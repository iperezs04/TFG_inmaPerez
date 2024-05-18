package com.tfginma.app.comentario.application;

import java.util.Optional;

import com.tfginma.app.comentario.domain.Comentario;
import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComentarioRequest {
	private Optional<Comentario> comentario;
	private Optional<User> usuario;
	private Optional<PeliculaSerie> peliSerie;
}
