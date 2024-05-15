package com.tfginma.app.peliculaserie.infrastructure;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfginma.app.peliculaserie.domain.Genero;
import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.peliculaserie.domain.Tipo;

public interface IPeliculaSerieController {
	@GetMapping("peliseries")
	public ResponseEntity<List<PeliculaSerie>> getAllPeliculaSerie();
	@GetMapping("peliseries/{tipo}")
	public ResponseEntity<List<PeliculaSerie>> getPeliculaSeriePorTipo(@PathVariable Tipo tipo);
	@GetMapping("peliseries/{genero}")
	public ResponseEntity<List<PeliculaSerie>> getPeliculaSeriePorGenero(@PathVariable Genero genero);
	@GetMapping("peliseries/ids")
	public ResponseEntity<List<PeliculaSerie>> getPeliculaSeriePorIds(@RequestBody List<Long> ids);
	@PostMapping("addpeliseries")
	public ResponseEntity<PeliculaSerie> addPeliSerie(@RequestBody PeliculaSerie peliculaSerie);
	@DeleteMapping("deletepeliseries/{id}")
	public ResponseEntity<PeliculaSerie> deletePeliSerie(@PathVariable Long id);
	@PutMapping("modifypeli/{id}")
	public ResponseEntity<PeliculaSerie> modifyPeliSerie(@PathVariable Long id, @RequestBody PeliculaSerie peliculaSerie);
}
