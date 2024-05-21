package com.manganet.entities;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
public class Usuario {

    @Id
    private String id;
    private String username;
    private String email;
    private String firebaseUid;
    
}