package com.manganet.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Capitulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Double numero;
	
	private String titulo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_publicacion")
	private LocalDate fechaPublicacion;
	
	@ManyToOne
	@JoinColumn(name = "obra_id")
	private Obra obra;
    
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
    
	@Column(name = "imagenes", columnDefinition = "TEXT[]")
	private String[] imagenes;

}
