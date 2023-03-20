package com.springboot.fundamentals.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyDepInsiteDepImplement implements MyDependencyInsiteDependency {
	
	//Práctica con logs
	Log LOGGER = LogFactory.getLog(this.getClass());
	
	//Se inyecta la interfaz MyOperation y se agrega el constructor de la clase en la que estamos y se grega la interfaz
	private MyOperation myOperation;
	//Constructor
	public MyDepInsiteDepImplement( MyOperation myOperation) {
		this.myOperation = myOperation;
	}
	
	@Override
	public void depPrint() {
		//Ahora se llama el método de MyOperation y este se imprimirá junto con el mensaje de esta clase
		int number = 20;
		//Con el log info podemos realizar seguimiento al funcionamiento de la app
		LOGGER.info("Se ha ingresado al método depPrint() desde la clase MyDepInsiteDepImplement");
		//El log debug sirve para encontrar fallos o errores en la app
		LOGGER.debug("Valor enviado en la variable number es: " + number);
		
		System.out.println("The result is: " + myOperation.operation(number));
		System.out.println("Hello from bean's implement with dependency, I'm DepInsiteDep!");
		
	}
}
