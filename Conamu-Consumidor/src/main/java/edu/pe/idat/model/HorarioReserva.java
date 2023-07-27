package edu.pe.idat.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario_reserva")
public class HorarioReserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Horario_Reserva")
	private int idHorarioReserva;
	
	@Column(name = "Fecha")
	private Date fecha;
	
	@Column(name = "Horario")
	private Time horario;
	
	@Column(name = "Numero_Mesas")
	private int numeroMesas;

	public int getIdHorarioReserva() {
		return idHorarioReserva;
	}

	public void setIdHorarioReserva(int idHorarioReserva) {
		this.idHorarioReserva = idHorarioReserva;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public int getNumeroMesas() {
		return numeroMesas;
	}

	public void setNumeroMesas(int numeroMesas) {
		this.numeroMesas = numeroMesas;
	}
	
	
}
