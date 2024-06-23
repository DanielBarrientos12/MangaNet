package com.manganet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manganet.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByEmail(String email);

}
