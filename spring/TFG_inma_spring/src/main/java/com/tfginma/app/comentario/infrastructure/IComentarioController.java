package com.tfginma.app.comentario.infrastructure;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tfginma.app.comentario.application.ComentarioRequest;
import com.tfginma.app.comentario.domain.Comentario;

public interface IComentarioController {
	@GetMapping("comentarioPeli/{id}")
	public ResponseEntity<List<Comentario>> getComentariosPeli(@PathVariable Long idPeli);
	@GetMapping("comentarioUsuario/{id}")
	public ResponseEntity<List<Comentario>> getComentariosUsuario(@PathVariable Long idUser);
	@PostMapping("publicar")
	public ResponseEntity<Comentario> publicarComentario(@RequestBody ComentarioRequest comentarioRequest);
	@DeleteMapping("borrar/{id}")
	public ResponseEntity<Comentario> borrarComentario(@PathVariable Long idComentario);
}
