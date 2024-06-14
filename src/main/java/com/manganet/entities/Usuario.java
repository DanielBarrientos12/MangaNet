package com.manganet.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String username;
    private String nombre;
    private String email;
    private String password;
    private String salt;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Obra> obras;
    
}