package com.springboot.fundamentals;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.function.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.springboot.fundamentals.bean.Empleados;
import com.springboot.fundamentals.bean.MyBean;
import com.springboot.fundamentals.bean.MyBeanValueProperties;
import com.springboot.fundamentals.bean.MyDependencyInsiteDependency;
import com.springboot.fundamentals.component.ComponentDependency;
import com.springboot.fundamentals.entity.User;
import com.springboot.fundamentals.repository.UserRepository;
import com.springboot.fundamentals.service.UserService;

@SpringBootApplication
public class FundamentalsApplication implements CommandLineRunner {
	
	//Para aplicar un log se usa el objeto Log
	private final Log LOGGER = LogFactory.getLog(FundamentalsApplication.class);
	
	//1-Inyectar las interfaces o dependencias
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyDependencyInsiteDependency myDependency;
	private MyBeanValueProperties myBeanValueProperties;
	private Empleados empleados;
	private UserService userService;
	//2-Se crea constructor de la clase con la dependencia(interfaz)
	@Autowired //Nota: Esta ntación ya no es necesaria en spring boot, pero en este caso se deja para recordar que existe
	/*3-Cuando hay mas de una calse que usa la misma dependencia, se debe indicar a SpringBoot cual se va usar
	utilizando la notación @Qualifier*/
	//4-Cuand se crea un bean, se debe agregar la interfaz al constructor
	
	//Persitencia de datos con JPA
	//Para que funcione la información creada de los usuario se debe inyectar el repositorio y se agrega al constructor
	private UserRepository userRepository;
	
	public FundamentalsApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean,
								MyDependencyInsiteDependency myDependency, /*MyBeanValueProperties myBeanValueProperties,*/
								Empleados empleados, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myDependency = myDependency;
		//this.myBeanValueProperties = myBeanValueProperties;
		this.empleados = empleados;
		this.userRepository = userRepository;
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	//En esta dependencia se ejecuta el método saludar()
	@Override
	public void run(String... args) throws Exception {
		ejemplosAnteriores();
		//Llammar método saveUser
		saveUserInDataBase();
		getInformationJpqlFormUser();
		//Si la transación es correcta se mostrará todo los usuariso test
		saveWithErrorTransactional();
	}
	
	//Método que trabaja la notación Transactional
	public void saveWithErrorTransactional() {
		//creación de los objetos de usuarios
		User test1 = new User("Transactional1", "trans1@trans.com", LocalDate.of(2015, 12, 5));
		User test2 = new User("Transactional2", "trans2@trans.com", LocalDate.of(2016, 5, 21));
		User test3 = new User("Transactional3", "trans1@trans.com", LocalDate.of(2020, 10, 18));
		User test4 = new User("Transactional4", "trans4@trans.com", LocalDate.of(2023, 2, 5));
		
		//Lista de usuarios que implementa los creados anteriormente
		List<User> transList = Arrays.asList(test1, test2, test3, test4);
		
		try {
			//Registro de usuarios con transactional
			userService.saveTransactional(transList);
		} catch (Exception e) {
			LOGGER.error("Error al registrar usuario: " + e.getMessage());
		}
		
		
		//LLamado de el método getAllUsers para que imprima la información con el logger
		userService.getAllUsers()
			.stream()
			.forEach(user -> LOGGER.info("User from Transactional Method: " + user));;
		
	}
	
	
	//Método par obtener información de la consulta JPQL
	private void getInformationJpqlFormUser() {
		//Se agrega log para mostrar info
		LOGGER.info("Usuario con el método findByUserEmail " + userRepository.findByUserEmail("danmail@domain.com")
					.orElseThrow(()-> new RuntimeException("No se encontó el email")));
	
		//buscar y ordenar a los uaurios de manera descendente
		userRepository.findAndSort("User", Sort.by("id").descending())
					.stream().forEach(user -> LOGGER.info("Usuario con método sort: " + user));
		
		//LLamando al query method
		userRepository.findByName("Angie")
			.stream().forEach(user -> LOGGER.info("Find users with query method: " + user));
		
		//Llammando al optinal query method, este formato es para Optinal<>
		LOGGER.info("Optional user found by email and name: " + userRepository.findByEmailAndName("angmail@domain.com", "Angie")
			.orElseThrow(()-> new RuntimeException("User no found")));
		
		//Este formato es para List<>
		userRepository.findByNameLike("%n%")
			.stream()
			.forEach(user -> LOGGER.info("User Found by name like: " + user));
		
		userRepository.findByNameOrEmail(null, "danmail@domain.com")
			.stream()
			.forEach(user -> LOGGER.info("Users found by name or email: " + user));
	
		userRepository.findByBirthDateBetween(LocalDate.of(2001, 6, 1), LocalDate.of(2020, 12, 31))
			.stream()
			.forEach(user -> LOGGER.info("Users found by date or birthdate: " + user));;
			
		userRepository.findByNameLikeOrderByIdAsc("%User%")
			.stream()
			.forEach(user -> LOGGER.info("Users found by like and order by idDesc or idAsc: " + user));
		
		userRepository.findByNameContainingOrderByIdDesc("User")
			.stream()
			.forEach(user -> LOGGER.info("Users found by Containing and order by idDesc or idAsc: " + user));
		
		LOGGER.info("Users found using JPQL from user: " + 
					userRepository.findByBirthDateAndEmail(LocalDate.of(2001, 11, 1), "jessmail@domain.com")
					.orElseThrow(()-> new RuntimeException("No se pudo hallar al usuario")));
	}
	
	
	//se crea un método para persistir la información de la BD
	private void saveUserInDataBase() {
		//Declaración de usuarios
		User user1 = new User("Daniel", "danmail@domain.com", LocalDate.of(1992, 10, 20));
		User user2 = new User("Angie", "ahrimail@domain.com", LocalDate.of(2020, 3, 25));
		User user3 = new User("Angie", "angmail@domain.com", LocalDate.of(1995, 8, 10));
		User user4 = new User("Paola", "paomail@domain.com", LocalDate.of(2022, 5, 25));
		User user5 = new User("Omar", "omimail@domain.com", LocalDate.of(2010, 2, 20));
		User user6 = new User("Jess", "jessmail@domain.com", LocalDate.of(2001, 11, 1));
		User user7 = new User("Mauricio", "maomail@domain.com", LocalDate.of(2009, 9, 12));
		User user8 = new User("User1", "user1@domain.com", LocalDate.of(2015, 12, 5));
		User user9 = new User("User2", "user2@domain.com", LocalDate.of(2015, 12, 5));
		User user10 = new User("User3", "user3@domain.com", LocalDate.of(2015, 12, 5));
		User user11 = new User("User4", "user4@domain.com", LocalDate.of(2015, 12, 5));
		
		//Crea una lista con los usuarios
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11);
		//Ahora se usa el método stream de Java para iterar cada usuario e indexarlo a la base de datos
		list.stream().forEach(userRepository::save);
		LOGGER.info(list);
	}
	
	private void ejemplosAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myDependency.depPrint();
		//System.out.println("Using values declared from de application properties: " + myBeanValueProperties.function());
		System.out.println(empleados.tarea());
		System.out.println(empleados.getInforme());	
		try {
			int value = 5/0;
		}catch (Exception e) {
			LOGGER.error("Esto es un error de practica " + e.getMessage());
		}
	}

}
