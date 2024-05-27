package com.tfginma.app.user.infrastructure;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfginma.app.peliculaserie.domain.PeliculaSerie;
import com.tfginma.app.user.domain.IUserService;
import com.tfginma.app.user.domain.User;

@RestController
@RequestMapping("user")
public class UserController implements IUserController{
	private final IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	@GetMapping("users/{nombre}")
	public ResponseEntity<List<Long>> getPelisSeriesFav(@PathVariable String nombre) {
		return userService
				.getPelisSeriesFav(nombre)
					.map(pelisSeriesFavoritas-> 
						new ResponseEntity<List<Long>>(pelisSeriesFavoritas,HttpStatus.OK))
							.orElse(new ResponseEntity<List<Long>>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("users/addPelifav/{nombre}/{idPeli}")
	public ResponseEntity<Long> addPeliFav(@PathVariable String nombre, @PathVariable Long idPeli){
		return userService
				.addPeliFav(nombre,idPeli)
				.map(pelisSeriesFavoritas-> 
					new ResponseEntity<Long>(pelisSeriesFavoritas,HttpStatus.OK))
						.orElse(new ResponseEntity<Long>(HttpStatus.NOT_FOUND));

		
	}
	@PostMapping("users/addUser/{emailuser}")
		public ResponseEntity<User> addUser(@PathVariable String emailuser){
			return new ResponseEntity<User>(userService.addUser(emailuser),HttpStatus.OK);

			
	}
	
}
