package edu.pe.idat.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Reserva")
	private int idReserva;
	
	@Column(name = "Cantidad_Personas")
	private int cantidadPersonas;
	
	@Column(name = "Comentarios_Adicionales")
	private String comentariosAdicionales;
	
	@Column(name = "Ticket")
	private String ticket;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Id_Consumidor")
	private Consumidor consumidor;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Id_Horario_Reserva")
	private HorarioReserva horarioReserva;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Id_Estado_Reserva")
	private EstadoReserva estadoReserva;

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public String getComentariosAdicionales() {
		return comentariosAdicionales;
	}

	public void setComentariosAdicionales(String comentariosAdicionales) {
		this.comentariosAdicionales = comentariosAdicionales;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public HorarioReserva getHorarioReserva() {
		return horarioReserva;
	}

	public void setHorarioReserva(HorarioReserva horarioReserva) {
		this.horarioReserva = horarioReserva;
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
}
