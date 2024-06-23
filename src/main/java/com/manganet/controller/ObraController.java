package com.manganet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.manganet.dto.ObraDTO;
import com.manganet.entities.Obra;
import com.manganet.services.ObraService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/obras")
public class ObraController {

	@Autowired
	public ObraService obraService;
	
	@Autowired
    private ObjectMapper objectMapper;

	@PostMapping("/crear")
    public ResponseEntity<?> createObra(@RequestParam("file") MultipartFile imagen, @RequestParam("obra") String obradtoStr) {
        try {
            ObraDTO obradto = objectMapper.readValue(obradtoStr, ObraDTO.class);
            Obra obra = obraService.createObra(obradto, imagen);
            return ResponseEntity.ok(obra);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

	@Operation(summary = "Get obra list", description = "Provide list Obras Management System", tags = { "Obras" })
	@GetMapping()
	public List<Obra> listarObra() {

		return obraService.listObras();
	}

	@Operation(summary = "Get an obra by its ID", description = "Provide an ID to look up a specific obra from the Obras Management System", tags = {
			"Obras" })
	@GetMapping("/{id}")
	public Obra getObra(
			@Parameter(description = "ID of the obra to be retrieved", required = true) @PathVariable Integer id) {

		Obra obra = obraService.getObra(id);
		return obra;
	}

}
