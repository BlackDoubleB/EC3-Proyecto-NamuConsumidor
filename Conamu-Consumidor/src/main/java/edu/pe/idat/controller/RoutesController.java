package edu.pe.idat.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.pe.idat.model.HorarioReserva;
import edu.pe.idat.model.Reserva;
import edu.pe.idat.service.ConsumidorService;
import edu.pe.idat.service.HorarioReservaService;
import edu.pe.idat.service.ReservaService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RoutesController {

	@Autowired
	private ConsumidorService consumidorService;
	
	@Autowired
	private HorarioReservaService horarioReservaService;
	
	@Autowired
	private ReservaService reservaService;
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/ingresar")
	public String ingresar(Model model) {
		return "ingresar";
	}
	
	@GetMapping("/crear-cuenta")
	public String crearCuenta(Model model) {
		return "crear-cuenta";
	}
	
	@GetMapping("/crear-reserva")
	
	//TODOS LOS QUE TIENEN ESTA ANOTACIÓN ESTAN SECURIZADOS @PreAuthorize PARA LAS RUTAS Y APIS
	// API ES PROTOCOLO DE COMUNICACION ENTRE CLIENTE Y SERVIDOR	
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String crearReserva(Model model, HttpServletRequest request) throws ParseException {
		
		var nombre = request.getUserPrincipal().getName();
		var usuario = consumidorService.findOneByUsuario(nombre);
		
		var horarios = horarioReservaService.findAllByFecha("");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("horarios", horarios);
		
		return "crear-reserva";
	}
	
	@GetMapping("/crear-reserva/combo-horario")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String comboHorarioReservas(Model model, @Param("fecha")String fecha) throws ParseException {
		
		List<HorarioReserva> horarios = horarioReservaService.findAllByFecha(fecha);
		
		List<HorarioReserva> horariosDisponibles = horarios.stream()
				.filter(horario -> horario.getNumeroMesas() > 0)
				.collect(Collectors.toList());
		
		model.addAttribute("horarios", horariosDisponibles);
		
		return "fragments/reservas-combo-horarios :: combo";
	}
	
	@GetMapping("/crear-reserva/modal-ver-ticket")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String modalTicketReserva(Model model, @Param("idReserva") String idReserva) throws ParseException {
		
		Reserva reserva = reservaService.findById(Integer.parseInt(idReserva));
		
		model.addAttribute("reserva", reserva);
		
		return "fragments/reservas-modal-ticket :: modal";
	}
	
	@GetMapping("/mis-reservas")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String verMisReservas(Model model, HttpServletRequest request) throws ParseException {
		
		var nombre = request.getUserPrincipal().getName();
		var usuario = consumidorService.findOneByUsuario(nombre);
		
		var reservas = reservaService.findAllByHorarioReservaFecha("");
		
		List<Reserva> reservasPropias = reservas.stream()
				.filter(horario -> horario.getConsumidor().getIdConsumidor() == usuario.getIdConsumidor())
				.collect(Collectors.toList());
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("reservas", reservasPropias);
		
		return "mis-reservas";
		
	}
	
	@GetMapping("/mis-reservas/table")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String obtenerTableMisReservas(Model model, HttpServletRequest request, @Param("fecha") String fecha) throws ParseException {
		
		var nombre = request.getUserPrincipal().getName();
		var usuario = consumidorService.findOneByUsuario(nombre);
		
		var reservas = reservaService.findAllByHorarioReservaFecha(fecha);
		
		List<Reserva> reservasPropias = reservas.stream()
				.filter(horario -> horario.getConsumidor().getIdConsumidor() == usuario.getIdConsumidor())
				.collect(Collectors.toList());
		
		model.addAttribute("reservas", reservasPropias);
		
		return "fragments/mis-reservas-table :: table";
		
	}
	
	@GetMapping("/mis-reservas/modal-editar")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String obtenerModalEditarReserva(Model model, @Param("idReserva") String idReserva) throws ParseException {
		
		var reserva = reservaService.findById(Integer.parseInt(idReserva));
		
		Date fechaReserva = reserva.getHorarioReserva().getFecha();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formato.format(fechaReserva);
		
		List<HorarioReserva> horarios = horarioReservaService.findAllByFecha(fechaFormateada);
		
		List<HorarioReserva> horariosDisponibles = horarios.stream()
				.filter(horario -> horario.getNumeroMesas() > 0)
				.collect(Collectors.toList());
		
		model.addAttribute("horarios", horariosDisponibles);
		
		model.addAttribute("reserva", reserva);
		
		return "fragments/mis-reservas-modal-editar :: modal";
		
	}
	
	@GetMapping("/mis-reservas/modal-ver-reserva")
	@PreAuthorize("hasRole('CONSUMIDOR')")
	public String obtenerModalVerReserva(Model model, @Param("idReserva") String idReserva) throws ParseException {
		
		Reserva reserva = reservaService.findById(Integer.parseInt(idReserva));
		
		model.addAttribute("reserva", reserva);
		
		return "fragments/mis-reservas-modal-ver:: modal";
		
	}
	
	
	@GetMapping("*")
    public String handleNotFound() {
        return "redirect:/";
    }
	
}
