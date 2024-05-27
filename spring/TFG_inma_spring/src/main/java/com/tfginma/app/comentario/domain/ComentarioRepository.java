package com.tfginma.app.comentario.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	
}
