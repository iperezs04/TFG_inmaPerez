package com.tfginma.app.comentario.infrastructure;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfginma.app.comentario.application.ComentarioRequest;
import com.tfginma.app.comentario.domain.Comentario;
import com.tfginma.app.comentario.domain.IComentarioService;

@RestController
@RequestMapping("comentario")
public class ComentarioController implements IComentarioController{
	
	private final IComentarioService comentarioService;
	
	public ComentarioController(IComentarioService comentarioService) {
		super();
		this.comentarioService = comentarioService;
	}

	@Override
	@GetMapping("comentarioPeli/{id}")
	public ResponseEntity<List<Comentario>> getComentariosPeli(@PathVariable Long idPeli) {
		return comentarioService.getComentariosPeli(idPeli)
					.map(comentariosPeli->
						new ResponseEntity<List<Comentario>>(comentariosPeli,HttpStatus.OK))
					.orElse(new ResponseEntity<List<Comentario>>(HttpStatus.NOT_FOUND));
	}

	@Override
	@GetMapping("comentarioUsuario/{id}")
	public ResponseEntity<List<Comentario>> getComentariosUsuario(@PathVariable Long idUser) {
		 return comentarioService.getComentariosUsuario(idUser)
				.map(comentariosUser->
				new ResponseEntity<List<Comentario>>(comentariosUser,HttpStatus.OK))
			.orElse(new ResponseEntity<List<Comentario>>(HttpStatus.NOT_FOUND));
	}

	@Override
	@PostMapping("publicar")
	public ResponseEntity<Comentario> publicarComentario(@RequestBody ComentarioRequest comentarioRequest) {
		return comentarioService
				.publicarComentario(comentarioRequest.getPeliSerie().get()
						,comentarioRequest.getComentario().get()
						,comentarioRequest.getUsuario().get())
				.map(comentarioPublicado->
					new ResponseEntity<Comentario>(comentarioPublicado, HttpStatus.CREATED))
						.orElse(new ResponseEntity<Comentario>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	@Override
	@DeleteMapping("borrar/{id}")
	public ResponseEntity<Comentario> borrarComentario(@PathVariable Long idComentario) {
		 return comentarioService
				.borrarComentario(idComentario)
				.map(comentarioBorrado->
					new ResponseEntity<Comentario>(comentarioBorrado, HttpStatus.NO_CONTENT))
						.orElse(new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND));
	}

}
