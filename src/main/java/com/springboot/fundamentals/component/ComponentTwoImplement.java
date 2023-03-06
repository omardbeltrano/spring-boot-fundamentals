package com.springboot.fundamentals.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {

	@Override
	public void saludar() {
		System.out.println("Hello from de second component!");
		
	}
	
}
