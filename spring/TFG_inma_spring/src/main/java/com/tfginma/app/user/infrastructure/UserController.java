package com.tfginma.app.user.infrastructure;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.IUserService;

@RestController
@RequestMapping("user")
public class UserController implements IUserController{
	private final IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	@GetMapping("users/{id}")
	public ResponseEntity<List<PeliculaSerie>> getPelisSeriesFav(@PathVariable Long id) {
		return userService
				.getPelisSeriesFav(id)
					.map(pelisSeriesFavoritas-> 
						new ResponseEntity<List<PeliculaSerie>>(pelisSeriesFavoritas,HttpStatus.OK))
							.orElse(new ResponseEntity<List<PeliculaSerie>>(HttpStatus.NOT_FOUND));
	}
	
}
