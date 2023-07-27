package edu.pe.idat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.model.Consumidor;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Integer> {

	Optional<Consumidor> findOneByUsuario(String usuario);
	
}
