package edu.pe.idat.request;

public class UsuarioCreateRequest {
	
	private String nombreCompletos;
	private String dni;
	private String telefono;
	private String correo;
	private String rol;
	private String usuario;
	private String contrasena;
	
	public String getNombreCompletos() {
		return nombreCompletos;
	}
	public void setNombreCompletos(String nombreCompletos) {
		this.nombreCompletos = nombreCompletos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
