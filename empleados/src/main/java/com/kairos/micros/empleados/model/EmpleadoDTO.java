package com.kairos.micros.empleados.model;

import java.io.Serializable;

public class EmpleadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private Long edad;
	private Long id;
	
	public EmpleadoDTO(){}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getEdad() {
		return edad;
	}

	public void setEdad(Long edad) {
		this.edad = edad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
