package com.springboot.fundamentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.fundamentals.bean.MyBean;
import com.springboot.fundamentals.bean.MyDependencyInsiteDependency;
import com.springboot.fundamentals.component.ComponentDependency;

@SpringBootApplication
public class FundamentalsApplication implements CommandLineRunner {
	
	//1-Inyectar las interfaces o dependencias
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyDependencyInsiteDependency myDependency;
	//2-Se crea constructor de la clase con la dependencia
	@Autowired //Nota: Esta ntación ya no es necesaria en spring boot, pero en este caso se deja para recordar que existe
	/*3-Cuando hay mas de una calse que usa la misma dependencia, se debe indicar a SpringBoot cual se va usar
	utilizando la notación @Qualifier*/
	//4-Cuand se crea un bean, se debe agregar la interfaz al constructor
	public FundamentalsApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean,
											 MyDependencyInsiteDependency myDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myDependency = myDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	//En esta dependencia se ejecuta el método saludar()
	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myDependency.depPrint();
	}

}
