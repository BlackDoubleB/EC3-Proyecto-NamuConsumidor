package edu.pe.idat.request;

public class ReservaCreateRequest {

	private String idConsumidor;
	private String idHorarioReserva;
	private String numeroPersonas;
	private String comentarios;
	
	public String getIdConsumidor() {
		return idConsumidor;
	}
	public void setIdConsumidor(String idConsumidor) {
		this.idConsumidor = idConsumidor;
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
