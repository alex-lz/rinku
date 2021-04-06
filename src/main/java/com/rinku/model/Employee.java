package com.rinku.model;

/**
 * Clase empleado.
 * Contiene los metodos getters y setters.
 * @author alejandro loaiza
 */
public class Employee {
	
	int numero;
	String nombre;
	String apellido_p;
	String apellido_m;
	String rol;
	String tipo;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido_p() {
		return apellido_p;
	}
	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}
	public String getApellido_m() {
		return apellido_m;
	}
	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}


