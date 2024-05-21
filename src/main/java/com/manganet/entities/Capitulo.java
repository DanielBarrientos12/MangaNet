package com.manganet.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Capitulo {

    @Id
    private String id;
    private String volumen;
    private String numeroCapitulo;
    private Obra obra; // Referencia a la obra
    private LocalDate fechaPublicacion;
    private String titulo;
    private List<String> imagenes;
    private String usuarioId;
}
