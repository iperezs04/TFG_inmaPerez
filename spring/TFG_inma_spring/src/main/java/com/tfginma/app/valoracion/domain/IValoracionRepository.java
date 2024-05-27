package com.tfginma.app.valoracion.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IValoracionRepository extends JpaRepository<Valoracion, Long> {

}
