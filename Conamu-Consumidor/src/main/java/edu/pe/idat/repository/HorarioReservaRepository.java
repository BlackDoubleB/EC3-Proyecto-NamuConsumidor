package edu.pe.idat.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.model.HorarioReserva;

public interface HorarioReservaRepository extends JpaRepository<HorarioReserva, Number> {

	List<HorarioReserva> findAllByFecha(Date fecha);
	
}
