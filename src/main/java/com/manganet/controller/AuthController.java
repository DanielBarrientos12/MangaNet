package com.manganet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manganet.dto.LoginReq;
import com.manganet.dto.RegisterReq;
import com.manganet.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterReq request) {
		try {
			ResponseEntity<?> response = authService.register(request);
			return response;
		} catch (IllegalStateException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Se ha producido un error\n" + e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginReq request) {
		try {
			return ResponseEntity.ok(authService.login(request));
		} catch (Exception e) {
			return ResponseEntity.status(401).body("Credenciales invalidas or user not registered");
		}
	}
}
