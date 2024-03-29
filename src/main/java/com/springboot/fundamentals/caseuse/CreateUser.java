package com.springboot.fundamentals.caseuse;

import org.springframework.stereotype.Component;

import com.springboot.fundamentals.entity.User;
import com.springboot.fundamentals.service.UserService;

@Component
public class CreateUser {
	
	private UserService userService;

	public CreateUser(UserService userService) {
		this.userService = userService;
	}

	public User save(User newUser) {
		return userService.save(newUser);
	}
	
	
}
