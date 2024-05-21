package com.manganet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
	
	private int id;
    private String username;
    private String email;
    private String firebaseUid;
    
}