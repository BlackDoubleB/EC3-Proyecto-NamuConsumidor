package edu.pe.idat.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consumidor")
public class Consumidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Consumidor")
	private int idConsumidor;
	
	@Column(name = "Usuario")
	private String usuario;
	
	@Column(name = "Contrasena")
	private String contrasena;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Id_Persona")
	private Persona persona;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Id_Rol")
	private Rol rol;

	public int getIdConsumidor() {
		return idConsumidor;
	}

	public void setIdConsumidor(int idConsumidor) {
		this.idConsumidor = idConsumidor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
