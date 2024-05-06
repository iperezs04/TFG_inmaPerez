package com.tfginma.app.peliculaserie.domain;

import java.util.List;

import com.tfginma.app.comentario.domain.Comentario;
import com.tfginma.app.user.domain.User;
import com.tfginma.app.valoracion.domain.Valoracion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "Peliculasyseries") 
public class PeliculaSerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(value = 1)
	private Long idPeliculaSerie;
	
	private String titulo;
	
	private Tipo tipo;
	
	private Genero genero;
	
	private String temporada;
	
	private String sinopsis;
	
	private String imagen;
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User usuario;
	private float valoracionMedia;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Comentario> comentario;


	@ManyToMany(cascade = CascadeType.ALL)
	private List<Valoracion> valoracion;
}
