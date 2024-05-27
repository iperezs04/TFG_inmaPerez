package com.tfginma.app.user.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IUserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByNombre(String nombre);
}
