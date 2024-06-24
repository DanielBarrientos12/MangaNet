package com.manganet.dto;

import java.time.LocalDate;
import java.util.List;

import com.manganet.entities.Demografia;
import com.manganet.entities.Estado;
import com.manganet.entities.Genero;
import com.manganet.entities.Tipo;
import com.manganet.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObra {

	private Integer id;
	private String nombre;
	private LocalDate fechaLanzamiento;
	private String descripcion;
	private Tipo tipo;
	private Estado estado;
	private String imagen;
	private int likes;
	private int dislikes;
	private Demografia demografia;
	private List<Usuario> usuarios;
	private List<Genero> generos;
}
