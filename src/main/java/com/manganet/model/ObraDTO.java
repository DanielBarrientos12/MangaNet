package com.manganet.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObraDTO {
	
	private Integer id;
	private String nombre;
	private LocalDate fechaLanzamiento;
    private String descripcion;
    private TipoDTO tipo;
    private EstadoDTO estado;
    private String imagen;
    private int likes;
    private int dislikes;
    private DemografiaDTO demografia;
    
}
