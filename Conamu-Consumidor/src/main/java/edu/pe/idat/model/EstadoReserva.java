package edu.pe.idat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_reserva")
public class EstadoReserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Estado_Reserva")
	private int idEstadoReserva;
	
	@Column(name = "Nombre_Estado")
	private String nombreEstado;
	
	public int getIdEstadoReserva() {
		return idEstadoReserva;
	}

	public void setIdEstadoReserva(int idEstadoReserva) {
		this.idEstadoReserva = idEstadoReserva;
	}
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
}
