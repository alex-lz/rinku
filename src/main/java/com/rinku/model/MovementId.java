package com.rinku.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@Embeddable
public class MovementId implements Serializable {

    @Column(name = "numero")
    private Long numero;

    @Column(name = "fecha")
    private String fecha;

    public MovementId() {
    }

    public MovementId(Long numero, String fecha) {
        this.numero = numero;
        this.fecha = fecha;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementId that = (MovementId) o;
        return numero.equals(that.numero) &&
                fecha.equals(that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, fecha);
    }
}
