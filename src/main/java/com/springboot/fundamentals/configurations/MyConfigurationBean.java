package com.springboot.fundamentals.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.fundamentals.bean.MyBean;
import com.springboot.fundamentals.bean.MyBeanImplement;
import com.springboot.fundamentals.bean.MyBeanTwoImplement;
import com.springboot.fundamentals.bean.MyDepInsiteDepImplement;
import com.springboot.fundamentals.bean.MyDependencyInsiteDependency;
import com.springboot.fundamentals.bean.MyOperation;
import com.springboot.fundamentals.bean.MyOperationImplement;

/*La notación @Configuration se usa oara indicarle a Spring Boot que vamos a realizar configuraciones adicionales
 relacionada a nuestros beans
 */
@Configuration
public class MyConfigurationBean {
	/*Con el bean se va llamar la interfaz MyBean con un método al que se debe asignar un nombre cualquiera(beanConfiguration)
	para retornar una nueva implementación de la clase MyBeanImplement*/
	@Bean
	public MyBean beanConfiguration() {
		/*Desde aquí la bean configuration se puede elegir que bean se va a llamar, 
		solo debe cambiar el nombre de la implementación, en ese caso MyBeanTwoImplement*/
		return new MyBeanTwoImplement();
	}
	
	//Se crea un nuevo bean para implementar la operación
	@Bean
	public MyOperation beanSumConfiguration() {
		return new MyOperationImplement();
	}
	
	/*Aquí se declara el bean que implementa la interfaz MyDependency... y se debe agregar como parámetro
	la interfaz que tiene como restricción en el constructor en la clase de la implementación*/
	@Bean
	public MyDependencyInsiteDependency beanDependencyConfiguration(MyOperation myOperation) {
		return new MyDepInsiteDepImplement(myOperation);
	}
}
