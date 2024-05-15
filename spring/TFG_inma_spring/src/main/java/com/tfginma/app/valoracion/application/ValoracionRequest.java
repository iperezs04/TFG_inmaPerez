package com.tfginma.app.valoracion.application;

import java.util.Optional;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;
import com.tfginma.app.valoracion.domain.Valoracion;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValoracionRequest {
	private Optional<PeliculaSerie> peliserie;
	private Optional<Valoracion> valoracion;
	private Optional<User> usuario;
}
