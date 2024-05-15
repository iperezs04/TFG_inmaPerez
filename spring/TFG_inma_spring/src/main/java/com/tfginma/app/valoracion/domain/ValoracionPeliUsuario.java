package com.tfginma.app.valoracion.domain;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(IDValoracionPeliUsuario.class)
public class ValoracionPeliUsuario {

	@Id 
	@ManyToOne
	private Valoracion valoracion;
	@Id
	@ManyToOne
	private User usuario;
	@Id
	@ManyToOne
	private PeliculaSerie peliculaSerie;

}
