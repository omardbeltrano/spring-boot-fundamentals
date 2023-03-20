package com.springboot.fundamentals.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.fundamentals.caseuse.GetUser;
import com.springboot.fundamentals.caseuse.GetUserImplement;
import com.springboot.fundamentals.service.UserService;

@Configuration
public class CaseUseConfiguration {
	
	//Creación de bean a partir de la clase GetUSer
	@Bean
	GetUser getUser(UserService userService) {
		//Retorno de la clase de la implementación
		return new GetUserImplement(userService);
	}
	
}
