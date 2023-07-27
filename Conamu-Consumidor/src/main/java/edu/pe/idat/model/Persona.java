package edu.pe.idat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Persona")
	private int idpersona;
	
	@Column(name = "Nombre_Completos")
	private String nombreCompletos;
	
	@Column(name = "Telefono")
	private String telefono;
	
	@Column(name = "Correo")
	private String correo;
	
	@Column(name = "Dni")
	private String dni;

	public int getIdpersona() {
		return idpersona;
	}

	public void setIdpersona(int idpersona) {
		this.idpersona = idpersona;
	}

	public String getNombreCompletos() {
		return nombreCompletos;
	}

	public void setNombreCompletos(String nombreCompletos) {
		this.nombreCompletos = nombreCompletos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
