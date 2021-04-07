package com.rinku.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase movimientos.
 * Contiene los metodos getters y setters.
 * @author alejandro loaiza
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "movements")
public class Movement implements Serializable {
	
	@EmbeddedId
    private MovementId movementId;
	
	private int entregas;
	private boolean cubrio;
	private String rol;
	
	public Movement() {

	}
	
	public Movement(MovementId movementId, int entregas, boolean cubrio, String rol) {
		this.movementId = movementId;
		this.entregas = entregas;
		this.cubrio = cubrio;
		this.rol = rol;
	}
	
	public MovementId getMovementId() {
		return movementId;
	}
	public void setMovementId(MovementId movementId) {
		this.movementId = movementId;
	}
	
	@Column(name = "entregas", nullable = false)
	public int getEntregas() {
		return entregas;
	}
	public void setEntregas(int entregas) {
		this.entregas = entregas;
	}
	
	@Column(name = "cubrio", nullable = false)
	public boolean isCubrio() {
		return cubrio;
	}
	public void setCubrio(boolean cubrio) {
		this.cubrio = cubrio;
	}
	
	@Column(name = "rol", nullable = false)
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

}
