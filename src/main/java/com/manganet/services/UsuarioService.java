package com.manganet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manganet.entities.Usuario;
import com.manganet.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public Usuario getUsuario(Integer id) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		
		if (usuarioOpt.isEmpty()) {
			return null;
		}
		
		return usuarioOpt.get();
	}

}
