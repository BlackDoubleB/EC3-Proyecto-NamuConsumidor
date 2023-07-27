package edu.pe.idat.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.pe.idat.model.Consumidor;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Consumidor usuario;
	
	public UserDetailsImpl(Consumidor usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_".concat(usuario.getRol().getNombreRol())));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return usuario.getContrasena();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		//return usuario.getEstadoTrabajador().getIdEstadoTrabajador() == 1;
		return true;
	}
	
	public String getNombres() {
		
		return usuario.getPersona().getNombreCompletos();
		
	}
	
	public String getRol() {
		
		return usuario.getRol().getNombreRol();
		
	}

}
