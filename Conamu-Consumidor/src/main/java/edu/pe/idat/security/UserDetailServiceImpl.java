package edu.pe.idat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Consumidor;
import edu.pe.idat.repository.ConsumidorRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Consumidor usuario = consumidorRepository.findOneByUsuario(username)
		.orElseThrow(() -> new UsernameNotFoundException("El Trabajador con usuario " + username + " no existe."));
		
		return new UserDetailsImpl(usuario);
	}
	
}
