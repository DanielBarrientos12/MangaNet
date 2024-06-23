package com.manganet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manganet.entities.Capitulo;

public interface CapituloRepository extends JpaRepository<Capitulo, Integer> {
	
	List<Capitulo> findByObraId(Integer obraId);
	Optional<Capitulo> findByObraIdAndNumero(Integer obraId, Double numero);

}
