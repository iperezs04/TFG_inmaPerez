package com.tfginma.app.peliculaserie.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IPeliSerieRepository extends JpaRepository<PeliculaSerie, Long> {

}
