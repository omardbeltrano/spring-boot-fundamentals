package com.springboot.fundamentals.bean;

public class DesarrolladorEmpleadoImplement implements Empleados {
	
	/*//Practica de inyección de dependencias usando Setters
	//1-Realizar la inyección de la interfaz que ya está en la línea 7
	//2-Luego se crea el setter de la interfaz
	
	public void setInformeNuevo(CrearInformes informeNuevo) {
		this.informeNuevo = informeNuevo;
	}*/



	private CrearInformes informeNuevo;

	public DesarrolladorEmpleadoImplement(CrearInformes informeNuevo) {
		this.informeNuevo = informeNuevo;
	}



	@Override
	public String tarea() {
		return "Deasarrolla páginas web con spring boot";
	}
	
	@Override
	public String getInforme() {
		System.out.println();
		return "Documento que contiene el código fuente sobre el desarrollo realizado. " + informeNuevo.getInforme();
	}
	
}
