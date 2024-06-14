package com.manganet.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CapituloDTO {
	
	private Integer id;
	private Double numero;
	private String titulo;
	private LocalDate fechaPublicacion;
    private ObraDTO obra;
    private UsuarioDTO usuario;
    private String[] imagenes;

}
