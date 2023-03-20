package com.springboot.fundamentals.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.springboot.fundamentals.dto.UserDto;
import com.springboot.fundamentals.entity.User;

/*Registros en base de datos con JpaRepository(Es una implementción de Jpa a nivel de interface
para agrega u obtner información)*/
//Heredamos(Extends) la interface UserRepository con la interface JpaRepository donde se mapea la entidad y el tipo de dato que se quiere manejar
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//Implementación y uso de JPQL que es un tipo de lenguaje SQL de JAVA pero solo permite el select, update and delete
	//consulta que devuelve un optional y notación query par la consulta jpql
	@Query("Select u from User u Where u.email=?1")
	Optional<User>  findByUserEmail(String email);
	
	//Otra consulta que retorna
	@Query("select u from User u where u.name like ?1%")
	List<User> findAndSort(String namer, Sort sort);
	
	//Creación de un query method, esto es una alternativa a jpql
	List<User> findByName(String Name);
	
	Optional<User> findByEmailAndName(String email, String name);
	
	//búsqueda por like
	List<User> findByNameLike(String name);
	
	//Búsqueda por OR
	List<User> findByNameOrEmail(String name, String email);
	
	//Búsqueda por fechas
	List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);
	
	//Búsqueda por like y idDesc o idAsc
	List<User> findByNameLikeOrderByIdAsc(String name);
	
	//Búsqueda por contain
	List<User> findByNameContainingOrderByIdDesc(String name);
	
	
	//Trabajando nuevamente con JPQL y named parameters
	//Se debe usar la dirección del paquete de la clase de donde provienen los campos junto con el constructor UserDto() en este caso
	@Query("select u from User u where u.birthDate = :birthDate or u.email = :email") //Aquií se implementa el nombre del parametro deseado
	//Para usar el parámetro asignado se debe usar la notación @Param antes del atributo
	Optional<User> findByBirthDateAndEmail(@Param("birthDate") LocalDate date, @Param("email") String email);
}
