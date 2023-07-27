package edu.pe.idat.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>  {

	Reserva findFirstByOrderByIdReservaDesc();
	
	List<Reserva> findAllByHorarioReservaFecha(Date fecha);
}
