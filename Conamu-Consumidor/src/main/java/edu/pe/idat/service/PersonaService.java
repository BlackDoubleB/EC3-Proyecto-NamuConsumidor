package edu.pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Persona;
import edu.pe.idat.repository.PersonaRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Transactional
	public Persona addPersona (Persona persona) {
		
		Persona personaDB = personaRepository.save(persona);
		
		return personaDB;
		
	}
}
