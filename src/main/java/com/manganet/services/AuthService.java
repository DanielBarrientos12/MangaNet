package com.manganet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manganet.dto.AuthResponse;
import com.manganet.dto.LoginReq;
import com.manganet.dto.RegisterReq;
import com.manganet.dto.Role;
import com.manganet.entities.Usuario;
import com.manganet.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginReq request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            Usuario user = (Usuario) authentication.getPrincipal();
            String token = jwtService.generateToken(user);

            return AuthResponse.builder()
                    .email(user.getEmail())
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            throw new IllegalStateException("Invalid credentials");
        }
    }

    public AuthResponse register(RegisterReq request) {
        Optional<Usuario> userFound = userRepository.findByEmail(request.getEmail());

        if (userFound.isPresent()) {
            throw new IllegalStateException("El usuario ya existe");
        }

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .email(user.getEmail())
                .token(jwtService.generateToken(user))
                .build();
    }
}
