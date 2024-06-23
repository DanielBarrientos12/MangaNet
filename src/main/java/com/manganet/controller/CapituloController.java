package com.manganet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manganet.dto.CapituloDTO;
import com.manganet.entities.Capitulo;
import com.manganet.services.CapituloService;

@RestController
@RequestMapping("/capitulos")
public class CapituloController {

	@Autowired
	public CapituloService capituloService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/crear")
    public ResponseEntity<Capitulo> crearCapitulo(
            @RequestParam("files") List<MultipartFile> imagenes,
            @RequestParam("capitulo") String capituloDtoStr) {
        try {
            CapituloDTO capituloDTO = objectMapper.readValue(capituloDtoStr, CapituloDTO.class);
            Capitulo capitulo = capituloService.crearCapitulo(capituloDTO, imagenes);
            return ResponseEntity.ok(capitulo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
	
	

}
