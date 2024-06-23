package com.manganet.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CapituloDTO {
	
    private Double numero;
    private String titulo;
    private Integer obraId;
    private Integer usuarioId;
    
    public LocalDate obtenerFechaPublicacion() {
        return LocalDate.now();
    }
}
