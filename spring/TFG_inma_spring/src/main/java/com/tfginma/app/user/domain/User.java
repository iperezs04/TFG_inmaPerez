package com.tfginma.app.user.domain;

import java.util.List;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	
	private Long idUser;
	private String nombre;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<PeliculaSerie> peliculaSerieFavorita;
}
