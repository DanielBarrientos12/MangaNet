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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/obras")
public class ObraController {

	@Autowired
	public ObraService obraService;

	@Autowired
	private ObjectMapper objectMapper;

	@Operation(summary = "Crear una nueva obra", description = "Permite crear una nueva obra en el sistema junto con su imagen y géneros asociados.", tags = {
			"Obras" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Obra creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Obra.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
	@PostMapping("/crear")
	public ResponseEntity<?> createObra(
			@Parameter(description = "Archivo de imagen de la obra", required = true) @RequestParam("file") MultipartFile imagen,
			@Parameter(description = "Datos de la obra en formato JSON", required = true) @RequestParam("obra") String obradtoStr) {
		try {
			ObraDTO obradto = objectMapper.readValue(obradtoStr, ObraDTO.class);
			Obra obra = obraService.createObra(obradto, imagen);
			return ResponseEntity.ok(obra);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@Operation(summary = "Obtener la lista de obras", description = "Proporciona una lista de todas las obras en el sistema.", tags = {
			"Obras" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de obras obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Obra.class))), })
	@GetMapping
	public List<Obra> listarObra() {
		return obraService.listObras();
	}

	@Operation(summary = "Obtener una obra por su ID", 
			description = "Proporciona una obra específica en el sistema, basada en el ID proporcionado.", 
			tags = {"Obras" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Obra obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Obra.class))),
			@ApiResponse(responseCode = "404", description = "Obra no encontrada", content = @Content) })
	@GetMapping("/{id}")
	public Obra getObra(
			@Parameter(description = "ID de la obra a ser obtenida", required = true) @PathVariable Integer id) {
		return obraService.getObra(id);
	}

}
