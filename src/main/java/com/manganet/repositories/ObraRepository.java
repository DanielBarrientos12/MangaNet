package com.manganet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manganet.entities.Obra;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
	
	Optional<Obra> findByNombre(String nombre);

}
