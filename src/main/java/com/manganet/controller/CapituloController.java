package com.manganet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manganet.dto.CapituloDTO;
import com.manganet.entities.Capitulo;
import com.manganet.services.CapituloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/capitulos")
public class CapituloController {

    @Autowired
    public CapituloService capituloService;

    @Autowired
    private ObjectMapper objectMapper;

    @Operation(summary = "Crear un nuevo capítulo", description = "Permite crear un nuevo capítulo en el sistema junto con sus imágenes asociadas.", tags = { "Capítulos" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Capítulo creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Capitulo.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content) })
    @PostMapping("/crear")
    public ResponseEntity<Capitulo> crearCapitulo(
            @Parameter(description = "Archivos de imágenes del capítulo", required = true) @RequestParam("files") List<MultipartFile> imagenes,
            @Parameter(description = "Datos del capítulo en formato JSON", required = true) @RequestParam("capitulo") String capituloDtoStr) {
        try {
            CapituloDTO capituloDTO = objectMapper.readValue(capituloDtoStr, CapituloDTO.class);
            Capitulo capitulo = capituloService.crearCapitulo(capituloDTO, imagenes);
            return ResponseEntity.ok(capitulo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @Operation(summary = "Obtener capítulos de una obra", description = "Proporciona una lista de todos los capítulos asociados a una obra específica.", tags = { "Capítulos" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de capítulos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Capitulo.class))), })
    @GetMapping("/obra/{obraId}")
    public List<Capitulo> getCapitulosByObra(
            @Parameter(description = "ID de la obra para obtener los capítulos", required = true) @PathVariable Integer obraId) {
        return capituloService.listCapitulosObra(obraId);
    }

    @Operation(summary = "Obtener un capítulo específico de una obra", description = "Proporciona un capítulo específico de una obra basada en el ID de la obra y el número del capítulo.", tags = { "Capítulos" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Capítulo obtenido exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Capitulo.class))),
        @ApiResponse(responseCode = "404", description = "Capítulo no encontrado", content = @Content) })
    @GetMapping("/obra/{obraId}/capitulo/{numeroCapitulo}")
    public ResponseEntity<Capitulo> getCapitulo(
            @Parameter(description = "ID de la obra", required = true) @PathVariable Integer obraId,
            @Parameter(description = "Número del capítulo", required = true) @PathVariable Double numeroCapitulo) {
        try {
            Capitulo capitulo = capituloService.getCapituloByObraAndNumero(obraId, numeroCapitulo);
            return new ResponseEntity<>(capitulo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
