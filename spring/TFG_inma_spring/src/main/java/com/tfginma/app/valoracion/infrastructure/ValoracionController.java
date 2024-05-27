package com.tfginma.app.valoracion.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfginma.app.valoracion.application.ValoracionRequest;
import com.tfginma.app.valoracion.domain.IValoracionService;
import com.tfginma.app.valoracion.domain.Valoracion;

@RestController
@RequestMapping("valoracion")
public class ValoracionController implements IValoracionController{

	private final IValoracionService valoracionService;
	public ValoracionController(IValoracionService valoracionService) {
		super();
		this.valoracionService = valoracionService;
	}

	@Override
	@GetMapping("media/{id}")
	public ResponseEntity<Float> getValoracionMedia(@PathVariable Long id) {
		return valoracionService.getValoracionMedia(id)
					.map(valoracion->
						new ResponseEntity<Float>(valoracion,HttpStatus.OK))
				.orElse(new ResponseEntity<Float>(HttpStatus.NOT_FOUND));
	}

	@Override
	@PostMapping("publicar")
	public ResponseEntity<Valoracion> publicarValoracion(@RequestBody ValoracionRequest valoracionRequest) {
		return valoracionService
				.publicarValoracion(
						 valoracionRequest.getValoracion().get()
						,valoracionRequest.getUsuario().get()
						,valoracionRequest.getPeliserie().get())
							.map(publicar->
								new ResponseEntity<Valoracion>(publicar,HttpStatus.OK))
				.orElse(new ResponseEntity<Valoracion>(HttpStatus.NOT_FOUND));
	}

}
