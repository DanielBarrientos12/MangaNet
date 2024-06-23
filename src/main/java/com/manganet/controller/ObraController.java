package com.manganet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.manganet.entities.Obra;
import com.manganet.services.ObraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/obras")
public class ObraController {
	
	@Autowired
	public ObraService obraService;
	
	@Operation(summary = "Get obra list", description = "Provide list Obras Management System", tags = { "Obras" })
	@GetMapping()
	public List<Obra> listarObra() {
		
		return obraService.listObras();		
	}
	
	@Operation(summary = "Get an obra by its ID", description = "Provide an ID to look up a specific obra from the Obras Management System", tags = { "Obras" })
	@GetMapping("/{id}")
	public Obra getObra(@Parameter(description = "ID of the obra to be retrieved", required = true) @PathVariable Integer id) {
		
		Obra obra = obraService.getObra(id);
		return obra;
	}

}
