package com.tfginma.app.user.infrastructure;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;

public interface IUserController {
	@GetMapping("users/{id}")
	public ResponseEntity<List<PeliculaSerie>> getPelisSeriesFav(@PathVariable Long id);
}
