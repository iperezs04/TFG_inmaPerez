package com.tfginma.app.valoracion.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfginma.app.valoracion.application.ValoracionRequest;
import com.tfginma.app.valoracion.domain.Valoracion;

public interface IValoracionController {
	@GetMapping("media/{id}")
	public ResponseEntity<Double> getValoracionMedia(@PathVariable Long id);
	@PostMapping("publicar")
	public ResponseEntity<Valoracion> publicarValoracion(@RequestBody ValoracionRequest valoracionRequest);
}
