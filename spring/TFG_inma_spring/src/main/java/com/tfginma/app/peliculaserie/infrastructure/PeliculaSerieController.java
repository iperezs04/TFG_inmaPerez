package com.tfginma.app.peliculaserie.infrastructure;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfginma.app.peliculaserie.domain.Genero;
import com.tfginma.app.peliculaserie.domain.IPeliculaSerieService;
import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.peliculaserie.domain.Tipo;

@RestController
@RequestMapping("peliculaserie")
public class PeliculaSerieController implements IPeliculaSerieController{
	private final IPeliculaSerieService peliculaSerieService;

	public PeliculaSerieController(IPeliculaSerieService peliculaSerieService) {
		super();
		this.peliculaSerieService = peliculaSerieService;
	}

	@Override
	@GetMapping("peliseries")
	public ResponseEntity<List<PeliculaSerie>> getAllPeliculaSerie() {
		return peliculaSerieService
				.getPeliculaSerie()
					.map(peliculasSeries->
						new ResponseEntity<List<PeliculaSerie>>(peliculasSeries,HttpStatus.OK))
            				.orElseGet(() -> new ResponseEntity<List<PeliculaSerie>>(HttpStatus.NOT_FOUND));
	}

	@Override
	@GetMapping("peliseries/{tipo}")
	public ResponseEntity<List<PeliculaSerie>> getPeliculaSeriePorTipo(@PathVariable Tipo tipo) {
		return peliculaSerieService
				.getPeliculaSeriePorTipo(tipo)
					.map(peliculaserie-> 
						new ResponseEntity<List<PeliculaSerie>>(peliculaserie,HttpStatus.OK))
                    		.orElseGet(() -> new ResponseEntity<List<PeliculaSerie>>(HttpStatus.NOT_FOUND));
	}

	@Override
	@GetMapping("peliseries/{genero}")
	public ResponseEntity<List<PeliculaSerie>> getPeliculaSeriePorGenero(@PathVariable Genero genero) {
		return peliculaSerieService
				.getPeliculaSeriePorGenero(genero)
					.map(peliculaserie-> 
						new ResponseEntity<List<PeliculaSerie>>(peliculaserie,HttpStatus.OK))
                    		.orElseGet(() -> new ResponseEntity<List<PeliculaSerie>>(HttpStatus.NOT_FOUND));
	}

	@Override
	@GetMapping("peliseries/ids")
	public ResponseEntity<List<PeliculaSerie>> getPeliculaSeriePorIds(@RequestBody List<Long> ids) {
		return peliculaSerieService
				.getPeliculaSeriePorIds(ids)
					.map(peliculaserie-> 
						new ResponseEntity<List<PeliculaSerie>>(peliculaserie,HttpStatus.OK))
                    		.orElseGet(() -> new ResponseEntity<List<PeliculaSerie>>(HttpStatus.NOT_FOUND));
	}

	@Override
	@PostMapping("addpeliseries")
	public ResponseEntity<PeliculaSerie> addPeliSerie(@RequestBody PeliculaSerie peliculaSerie) {
		return peliculaSerieService
				.addPeliSerie(peliculaSerie)
					.map(peliculaSerieAnadida->
						new ResponseEntity<PeliculaSerie>(peliculaSerieAnadida, HttpStatus.CREATED))
							.orElse(new ResponseEntity<PeliculaSerie>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@Override
	@DeleteMapping("deletepeliseries/{id}")
	public ResponseEntity<PeliculaSerie> deletePeliSerie(@PathVariable Long id) {
		return peliculaSerieService
				.deletePeliSerie(id)
					.map(peliculaSerieBorrada->
						new ResponseEntity<PeliculaSerie>(peliculaSerieBorrada, HttpStatus.NO_CONTENT))
							.orElse(new ResponseEntity<PeliculaSerie>(HttpStatus.NOT_FOUND));
	}

	@Override
	@PutMapping("modifypeli/{id}")
	public ResponseEntity<PeliculaSerie> modifyPeliSerie(@PathVariable Long id,@RequestBody PeliculaSerie peliculaSerie) {
		return peliculaSerieService
				.modifyPeliSerie(id,peliculaSerie)
					.map(peliculaSerieModificada->
						new ResponseEntity<PeliculaSerie>(peliculaSerieModificada, HttpStatus.OK))
							.orElse(new ResponseEntity<PeliculaSerie>(HttpStatus.NOT_MODIFIED));
	}
	
}
