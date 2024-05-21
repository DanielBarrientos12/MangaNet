package com.manganet.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CapituloDTO {
	
	private int id;
    private Double numeroCapitulo;
    private ObraDTO obra; //en la base de datos es un entero
    private LocalDate fechaPublicacion;
    private String titulo;
    private List<String> imagenes;
    private int usuarioId;

}
