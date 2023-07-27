package edu.pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pe.idat.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Number> {

}
