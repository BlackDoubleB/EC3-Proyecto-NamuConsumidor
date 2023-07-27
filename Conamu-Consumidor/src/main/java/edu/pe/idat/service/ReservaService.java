package edu.pe.idat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Consumidor;
import edu.pe.idat.model.EstadoReserva;
import edu.pe.idat.model.HorarioReserva;
import edu.pe.idat.model.Reserva;
import edu.pe.idat.repository.ReservaRepository;
import edu.pe.idat.request.ReservaCreateRequest;
import edu.pe.idat.request.ReservaUpdateRequest;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaService {

	@Autowired
	private ConsumidorService consumidorService;
	
	@Autowired
	private HorarioReservaService horarioReservaService;
	
	@Autowired
	private EstadoReservaService estadoReservaService;
	
	@Autowired
	private ReservaRepository reservaRepository;

//TODO LO QUE VEREMOS A CONTINUACION SON METODOS PARA GESTIONAR LAS OPERACIONES DE CREAR RESERVA
	
//	create: Crea una nueva reserva en la base de datos con los detalles proporcionados en la solicitud (request).
//  También genera un número de ticket para la reserva. Luego, guarda la reserva creada en la base de datos.
	
	public Reserva create(ReservaCreateRequest request) {
		
		Consumidor consumidor = consumidorService.findById(Integer.parseInt(request.getIdConsumidor()));
		
		HorarioReserva horario = horarioReservaService.findById(Integer.parseInt(request.getIdHorarioReserva()));
		
		EstadoReserva estado = estadoReservaService.findById(1);
		
		Reserva ultimaReserva = reservaRepository.findFirstByOrderByIdReservaDesc();
		String ticket = "A00001";
		
		if(ultimaReserva != null) {
			String ultimoTicket = ultimaReserva.getTicket();
			String numeros = ultimoTicket.substring(1);
			int t = Integer.parseInt(numeros);
			
			ticket = String.format("A%05d", t);
		}
		
		
		Reserva reserva = new Reserva();
		reserva.setCantidadPersonas(Integer.parseInt(request.getNumeroPersonas()));
		reserva.setComentariosAdicionales(request.getComentarios());
		reserva.setTicket(ticket);
		reserva.setConsumidor(consumidor);
		reserva.setEstadoReserva(estado);
		reserva.setHorarioReserva(horario);
		
		return reservaRepository.save(reserva);
		
	}
	
//update: Actualiza una reserva existente en la base de datos con la información proporcionada en la solicitud (request). 
//Busca la reserva por su ID y, si se encuentra, actualiza los detalles y guarda los cambios en la base de datos.
	public void update(ReservaUpdateRequest request) {
		
		var reserva = reservaRepository.findById(Integer.parseInt(request.getIdReserva()));
		
		if(!reserva.isPresent()) {
			throw new EntityNotFoundException("No se encontró la Reserva con id ".concat(request.getIdReserva()));
		}
		
		HorarioReserva horario = horarioReservaService.findById(Integer.parseInt(request.getIdHorarioReserva()));
		
		var reservaActual = reserva.get();
		reservaActual.setCantidadPersonas(Integer.parseInt(request.getNumeroPersonas()));
		reservaActual.setComentariosAdicionales(request.getComentarios());
		reservaActual.setHorarioReserva(horario);
		
		reservaRepository.save(reservaActual);
		
	}
	
	
//	 Busca y devuelve una lista de reservas según la fecha del horario de reserva proporcionada. 
//   Si no se proporciona ninguna fecha, se utiliza la fecha actual.
	public List<Reserva> findAllByHorarioReservaFecha(String fecha) throws ParseException{
		
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		
		if(fecha != "") {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate = dateFormat.parse(fecha);
			sqlDate = new java.sql.Date(parsedDate.getTime());			
		}
		
		return reservaRepository.findAllByHorarioReservaFecha(sqlDate);
		
	}
//  Busca una reserva por su ID y devuelve el objeto Reserva correspondiente. 
// Si no se encuentra ninguna reserva con el ID dado, se lanza una excepción.
	public Reserva findById(Integer idReserva) {
		
		var reserva = reservaRepository.findById(idReserva);
		
		if(!reserva.isPresent()) {
			throw new EntityNotFoundException("No se encontró el estado con id ".concat(idReserva.toString()));
		}
		
		return reserva.get();
		
	}
	
}
