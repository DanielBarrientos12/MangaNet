package com.manganet.entities;

import java.util.List;

import lombok.Data;

import com.manganet.model.Demografia;
import com.manganet.model.Genero;
import com.manganet.model.TipoObra;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
public class Obra {

	@Id
    private String id;
    private String titulo;
    private String descripcion;
    private TipoObra tipo;
    private List<Genero> generos;
    private String estado;
    private String imagen;
    private int likes;
    private int dislikes;
    private double rating;
    private Demografia demografia;
    
}