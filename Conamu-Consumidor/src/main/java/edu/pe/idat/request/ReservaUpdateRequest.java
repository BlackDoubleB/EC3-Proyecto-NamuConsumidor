package edu.pe.idat.request;

public class ReservaUpdateRequest {
	private String idReserva;
	private String idHorarioReserva;
	private String numeroPersonas;
	private String comentarios;
	
	public String getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	public String getIdHorarioReserva() {
		return idHorarioReserva;
	}
	public void setIdHorarioReserva(String idHorarioReserva) {
		this.idHorarioReserva = idHorarioReserva;
	}
	public String getNumeroPersonas() {
		return numeroPersonas;
	}
	public void setNumeroPersonas(String numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
