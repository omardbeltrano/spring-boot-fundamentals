package com.springboot.fundamentals.bean;

public class MyBeanValuePropertiesImplement implements MyBeanValueProperties {

	/*Se debe declarar las variables y un constructor para que funcionen los parametros(name, lastName)
	declarados en el bean*/
	private String name;
	private String lastName;
	
	public MyBeanValuePropertiesImplement(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}


	@Override
	public String function() {
		
		//Retorno de las variables que se conectan con los valores que están en el bean
		return name + "-" + lastName;
	}

}
