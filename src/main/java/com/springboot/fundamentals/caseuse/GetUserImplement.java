package com.springboot.fundamentals.caseuse;

import java.util.List;

import com.springboot.fundamentals.entity.User;
import com.springboot.fundamentals.service.UserService;

public class GetUserImplement implements GetUser {

	//Inyección de la clase userService
	private UserService userService;
	
	//Constructor para que sirva la inyección
	public GetUserImplement(UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	public List<User> getAll(){
		return userService.getAllUsers();
	}
	
	//La configuración de dependenacias y bean está en la clase CaseUseConfiguration
}
