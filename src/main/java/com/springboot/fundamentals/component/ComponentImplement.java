package com.springboot.fundamentals.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {

	@Override
	public void saludar() {
		System.out.println("How's it going I came form the componente...");
	}
	

}
