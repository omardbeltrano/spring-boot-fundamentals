package com.springboot.fundamentals.dto;

import java.time.LocalDate;

public class UserDto {
	//Aquí en el dto se declaran los campos que se quieren implementar en el Query JPQL
	private Long id;
	private String name;
	private LocalDate birthDate;
	
	public UserDto() {
		
	}
	
	public UserDto(Long id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
