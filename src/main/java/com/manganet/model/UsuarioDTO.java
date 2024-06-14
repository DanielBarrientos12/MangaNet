package com.manganet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
	
	private Integer id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private String salt;
    
}