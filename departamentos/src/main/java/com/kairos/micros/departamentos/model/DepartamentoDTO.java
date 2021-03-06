package com.kairos.micros.departamentos.model;

import java.io.Serializable;
import java.util.Set;

public class DepartamentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private Long id;
	private Set<EmpleadoDTO> empleados;
	
	public DepartamentoDTO(){}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<EmpleadoDTO> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(Set<EmpleadoDTO> empleados) {
		this.empleados = empleados;
	}
	
	
	
}
