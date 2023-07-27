package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Rol;
import edu.pe.idat.repository.RolesRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rolesRepository;
	
	// findAll: Este método devuelve una lista de todos los roles de usuario presentes en la base de datos. 
	// Utiliza el método findAll del repositorio rolesRepository para obtener todos los roles.
	public List<Rol> findAll(){
		
		return rolesRepository.findAll();
		
	}
	
	
	//findById: Este método busca un rol de usuario por su ID (idRol) y devuelve el objeto Rol correspondiente. 
	//Si no se encuentra ningún rol con el ID dado, se lanza una excepción EntityNotFoundException indicando que no se encontró el rol.
	public Rol findById(Integer idRol) {
		
		var rol = rolesRepository.findById(idRol);
		
		if(!rol.isPresent()) {
			throw new EntityNotFoundException("No se encontró el rol con id ".concat(idRol.toString()));
		}
		
		return rol.get();
		
	}
}
