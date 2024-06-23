package com.manganet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manganet.dto.AuthResponse;
import com.manganet.dto.LoginReq;
import com.manganet.dto.RegisterReq;
import com.manganet.dto.Role;
import com.manganet.entities.Usuario;
import com.manganet.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	public UserRepository userRepository;

	public ResponseEntity<?> register(RegisterReq request) {
		Optional<Usuario> userFound = userRepository.findByEmail(request.getEmail());

		if (userFound.isPresent()) {
			throw new IllegalStateException("El usuario ya existe");
		}

		Usuario user = new Usuario();
		user.setNombre(request.getNombre());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setUsername(request.getUsername());
		user.setRole(Role.USER);

		userRepository.save(user);

		return ResponseEntity.ok(user);
	}
	
	public AuthResponse login(LoginReq request) {
		try {
			Optional<Usuario> userOpt = userRepository.findByEmail(request.getEmail());
			
			if (userOpt.isPresent() && userOpt.get().getPassword().equals(request.getPassword())) {
				AuthResponse response = AuthResponse.builder()
						.username(userOpt.get().getUsername())
						.token("1234567")
						.build();
				return response;
			} else {
				throw new Exception("Invalid credentials");
			}
			
		} catch (Exception e) {
			throw new IllegalStateException("An error has ocurred");
		}
	}
}
