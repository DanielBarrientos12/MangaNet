package com.manganet.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ObraDTO {
	
	private String nombre;
	private LocalDate fechaLanzamiento;
    private String descripcion;
    private Integer tipoId;
    private Integer estadoId;
    private Integer demografiaId;
    
}
