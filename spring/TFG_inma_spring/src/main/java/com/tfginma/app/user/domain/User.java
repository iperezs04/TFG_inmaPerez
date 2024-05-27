package com.tfginma.app.user.domain;

import java.util.List;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class User {

	@Id
	@Min(value = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String nombre;
	
	
	@ElementCollection
    @CollectionTable(name = "user_pelicula_favorita", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "pelicula_id")
	private List<Long> peliculaSerieFavorita;
	
	

	public User(String nombre) {
		super();
		this.nombre = nombre;
	}



	public User(String nombre, List<Long> peliculaSerieFavorita) {
		super();
		this.nombre = nombre;
		this.peliculaSerieFavorita = peliculaSerieFavorita;
	}
	
	
}
