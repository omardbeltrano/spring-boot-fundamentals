package com.springboot.fundamentals.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.springboot.fundamentals.bean.CrearInformes;
import com.springboot.fundamentals.bean.DesarrolladorEmpleadoImplement;
import com.springboot.fundamentals.bean.Empleados;
import com.springboot.fundamentals.bean.Informe;
import com.springboot.fundamentals.bean.MyBean;
import com.springboot.fundamentals.bean.MyBeanImplement;
import com.springboot.fundamentals.bean.MyBeanTwoImplement;
import com.springboot.fundamentals.bean.MyBeanValueProperties;
import com.springboot.fundamentals.bean.MyBeanValuePropertiesImplement;
import com.springboot.fundamentals.bean.MyDepInsiteDepImplement;
import com.springboot.fundamentals.bean.MyDependencyInsiteDependency;
import com.springboot.fundamentals.bean.MyMultiplicationOperationImplement;
import com.springboot.fundamentals.bean.MyOperation;
import com.springboot.fundamentals.bean.MyOperationImplement;
import com.springboot.fundamentals.bean.MySubstractOperationImplement;
import com.springboot.fundamentals.entity.User;
import com.springboot.fundamentals.pojo.UserProperties;

/*La notación @Configuration se usa oara indicarle a Spring Boot que vamos a realizar configuraciones adicionales
 relacionada a nuestros beans
 */
@Configuration
//Notación para el uso de las propiedades de connection.properties
//@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(UserProperties.class)

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
		//Sum Operation
		//return new MyOperationImplement();
		//Substract Operation
		return new MySubstractOperationImplement();
	}
	
	/*Aquí se declara el bean que implementa la interfaz MyDependency... y se debe agregar como parámetro
	la interfaz que tiene como restricción en el constructor en la clase de la implementación*/
	@Bean
	public MyDependencyInsiteDependency beanDependencyConfiguration(MyOperation myOperation) {
		return new MyDepInsiteDepImplement(myOperation);
	}
	
	//Se crea un nuevo bean como práctica de setters
	@Bean 
	public CrearInformes beanCrearInformes() {
		return new Informe();
	}
	
	@Bean
	public Empleados beanEmpleadoTask(CrearInformes crearInformes) {
		return new DesarrolladorEmpleadoImplement(crearInformes);
	}
	
	//Creación de un bean para la inyección de la configuración de la base de datos a nivel de clases
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:mem:test");
		dataSourceBuilder.username("sa");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}
	
	/*@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${driver}")
	private String driver;
	
	@Value("${username}")
	private String userName;
	
	@Value("${password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driver);
		dataSourceBuilder.url(jdbcUrl);
		dataSourceBuilder.username(userName);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}*/
}
