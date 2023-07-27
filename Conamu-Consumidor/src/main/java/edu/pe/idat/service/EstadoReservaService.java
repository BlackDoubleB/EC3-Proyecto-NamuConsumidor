package edu.pe.idat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.EstadoReserva;
import edu.pe.idat.repository.EstadoReservaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EstadoReservaService {

	@Autowired
	private EstadoReservaRepository estadoReservaRepository;
	
	public EstadoReserva findById(Integer idEstadoReserva) {
		
		var estado = estadoReservaRepository.findById(idEstadoReserva);
		
		if(!estado.isPresent()) {
			throw new EntityNotFoundException("No se encontró el estado con id ".concat(idEstadoReserva.toString()));
		}
		
		return estado.get();
		
	}
	
}
