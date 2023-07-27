package edu.pe.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pe.idat.model.Reserva;
import edu.pe.idat.request.ReservaCreateRequest;
import edu.pe.idat.request.ReservaUpdateRequest;
import edu.pe.idat.response.TransactionResponse;
import edu.pe.idat.service.ReservaService;

@RestController
@RequestMapping("/api/reserva")
public class ReservaApiController {

	@Autowired
	private ReservaService reservaService;
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public TransactionResponse createReserva(@RequestBody ReservaCreateRequest request) {
		try {
			
			Reserva reserva = reservaService.create(request);
			
			return new TransactionResponse(true, Integer.toString(reserva.getIdReserva()));
			
		}catch(Exception e) {
			
			return new TransactionResponse(false, "Ocurrió un error al registrar la Reserva.");
			
		}
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public TransactionResponse createReserva(@RequestBody ReservaUpdateRequest request) {
		try {
			
			reservaService.update(request);
			
			return new TransactionResponse(true, "La Reserva se ha modificado con éxito.");
			
		}catch(Exception e) {
			
			return new TransactionResponse(false, "Ocurrió un error al registrar la Reserva.");
			
		}
	}
	
	
}
