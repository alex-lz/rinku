package com.rinku.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase empleado.
 * Contiene los metodos getters y setters.
 * @author alejandro loaiza
 */
@Entity
@Table(name = "employees")
public class Employee {
	
	private long numero;
	private String nombre;
	private String apellido_p;
	private String apellido_m;
	private String rol;
	private String tipo;
	
	public Employee() {
		  
    }
 
	public Employee(String nombre, String apellido_p, String apellido_m, String rol, String tipo) {
		this.nombre = nombre;
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		this.rol = rol;
		this.tipo = tipo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	
	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "apellido_", nullable = false)
	public String getApellido_p() {
		return apellido_p;
	}
	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}
	
	@Column(name = "apellido_m", nullable = false)
	public String getApellido_m() {
		return apellido_m;
	}
	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}
	
	@Column(name = "rol", nullable = false)
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@Column(name = "tipo", nullable = false)
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}


