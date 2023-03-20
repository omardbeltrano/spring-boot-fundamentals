package com.springboot.fundamentals.controller;

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

import com.springboot.fundamentals.caseuse.CreateUser;
import com.springboot.fundamentals.caseuse.DeleteUser;
import com.springboot.fundamentals.caseuse.GetUser;
import com.springboot.fundamentals.caseuse.UpdateUser;
import com.springboot.fundamentals.entity.User;

@RestController
//Implementación de ruta
@RequestMapping("/api/users")
public class UserRestController {
	//Creación de un servicio CRUD
	//Inyección de la clase GetUser desde caseuse
	private GetUser getUser;
	//inyeccionde la clase CreateUSer
	private CreateUser createUser;
	private DeleteUser deleteUser;
	private UpdateUser updateUser;

	public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
		this.getUser = getUser;
		this.createUser = createUser;
		this.deleteUser = deleteUser;
		this.updateUser = updateUser;
	}
	
	//Método que regrese todos los usuarios
	@GetMapping("/")
	List<User> get(){
		return getUser.getAll();
	}
	
	//Método para crear usuarios que llama al mpetodo save desde CreateUser
	@PostMapping("/")
	ResponseEntity<User> newUser(@RequestBody User newUser){
		return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
	}
	
	//Método para eliminar usuario a través del ID
	@DeleteMapping("/{id}")
	ResponseEntity deleteUSer(@PathVariable Long id) {
		deleteUser.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){
		return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
	}
}
