package com.tfginma.app.valoracion.domain;

import java.io.Serializable;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class IDValoracionPeliUsuario implements Serializable{

	private Valoracion valoracion;
	private User usuario;
	private PeliculaSerie peliculaSerie;
}
