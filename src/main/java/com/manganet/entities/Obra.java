package com.manganet.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Obra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_lanzamiento")
	private LocalDate fechaLanzamiento;
    
	private String descripcion;
    
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;
    
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;
    
	private String imagen;
    
	private int likes;
    
	private int dislikes;
    
	@ManyToOne
	@JoinColumn(name = "demografia_id")
	private Demografia demografia;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "favorito",
			joinColumns = @JoinColumn(name = "obra_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuarios;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "asignaciones_generos",
			joinColumns = @JoinColumn(name = "obra_id"),
			inverseJoinColumns = @JoinColumn(name = "genero_id"))
	private List<Genero> generos;
	
}
