package com.springboot.fundamentals.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.fundamentals.bean.MyBeanValueProperties;
import com.springboot.fundamentals.bean.MyBeanValuePropertiesImplement;

@Configuration
public class ValuesConfiguration {
	/*
	//Se llaman los valures declarados en el application properties con la notación @Value
	@Value("${value.name}")
	//Luego se inyecta el valor
	private String name;
	
	@Value("${value.lastname}")
	private String lastName;
	
	@Value("${value.random}")
	private String ramdom;
	
	//Depués de inyectar los valores se crea un Bean agregando la clase que se va a implementar
	@Bean
	public MyBeanValueProperties function() {
		return new MyBeanValuePropertiesImplement(name, lastName);
	}*/
	
}
