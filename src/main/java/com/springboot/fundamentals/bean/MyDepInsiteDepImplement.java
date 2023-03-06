package com.springboot.fundamentals.bean;

public class MyDepInsiteDepImplement implements MyDependencyInsiteDependency {
	
	//Se inyecta la interfaz MyOperation y se agrega el constructor de la clase en la que estamos y se grega la interfaz
	private MyOperation myOperation;
	
	public MyDepInsiteDepImplement( MyOperation myOperation) {
		this.myOperation = myOperation;
	}
	
	@Override
	public void depPrint() {
		//Ahora se llama el método de MyOperation y este se imprimirá junto con el mensaje de esta clase
		int number = 10;
		System.out.println("The result is: " + myOperation.sum(number));
		System.out.println("Hello from bean's implemente with dependency, I'm DepInsiteDep!");
		
	}
}
