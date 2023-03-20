package com.springboot.fundamentals.caseuse;

import org.springframework.stereotype.Component;

import com.springboot.fundamentals.entity.User;
import com.springboot.fundamentals.service.UserService;

@Component
public class UpdateUser {
	
	private UserService userService;

	public UpdateUser(UserService userService) {
		this.userService = userService;
	}

	public User update(User newUser, Long id) {
		return userService.update(newUser, id);
	}
}
