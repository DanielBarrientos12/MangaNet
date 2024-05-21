package com.manganet.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObraDTO {
	
	private int id;
	private String titulo;
    private String descripcion;
    private TipoObra tipo;
    private List<Genero> generos;
    private String estado;
    private String imagen;
    private int likes;
    private int dislikes;
    private Demografia demografia;
	
}
