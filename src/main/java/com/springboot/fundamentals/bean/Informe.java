package com.springboot.fundamentals.bean;

public class Informe implements CrearInformes{
	@Override
	public String getInforme() {
		return "Informe Creado con éxito!";
	}
}
