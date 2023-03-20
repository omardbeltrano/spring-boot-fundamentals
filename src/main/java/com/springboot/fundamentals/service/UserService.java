package com.springboot.fundamentals.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.springboot.fundamentals.entity.User;
import com.springboot.fundamentals.repository.UserRepository;

//Se trabajará la notación Transactional la cual sirve para hacer rolback de la transacciones realizadas en base de datos

@Service
public class UserService {
	//Se crea un Log
	private final Log LOG = LogFactory.getLog(UserService.class); 
	//Se inyecta la dependencia o clase
	private UserRepository userRepository;

	
	//Constructor
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	/*Método para el uso de transactional que contiene una lista de la entidad User como parámetro
	 Cuando se utiliza @Transactional en un método, Spring maneja automáticamente la transacción asociada a ese método. 
	 Si todas las operaciones dentro del método se ejecutan sin problemas, la transacción se confirma. 
	 Si ocurre un error en alguna de las operaciones dentro del método, Spring se encarga de hacer un rollback 
	 y deshacer todas las operaciones dentro de la transacción.*/
	public void saveTransactional(List<User> users) {
		users.stream()
		.peek(user -> LOG.info("Usurio incertado: " + user))
		//Una manera de escribir o llamar a la clase inyectada
		//.forEach(user -> userRepository.save(user));
		//otra manera de escribir es con métodos de referencias
		.forEach(userRepository::save);
	}
	
	//Método que retorna una lista de usuarios
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	//Registro a nivel de base de datos
	public User save(User newUser) {
		return userRepository.save(newUser); 
	}

	public void delete(Long id) {
		userRepository.delete(new User(id));
		
	}

	public User update(User newUser, Long id) {
		//búsqueda por id
		return userRepository.findById(id).map(user -> {
			//Actualización de los datos del usuario encontrado
			user.setEmail(newUser.getEmail());
			user.setBirthDate(newUser.getBirthDate());
			user.setName(newUser.getName());
			return userRepository.save(user);
		}).get();
	}
}
