package com.manganet.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.manganet.dto.ObraDTO;
import com.manganet.entities.Demografia;
import com.manganet.entities.Estado;
import com.manganet.entities.Obra;
import com.manganet.entities.Tipo;
import com.manganet.repositories.DemografiaRepository;
import com.manganet.repositories.EstadoRepository;
import com.manganet.repositories.ObraRepository;
import com.manganet.repositories.TipoRepository;

@Service
public class ObraService {

	@Autowired
	public ObraRepository obraRepository;

	@Autowired
	public TipoRepository tipoRepository;

	@Autowired
	public EstadoRepository estadoRepository;

	@Autowired
	public DemografiaRepository demografiaRepository;
	
	@Autowired
    public FileService fileService;

	public Obra createObra(ObraDTO obraDto, MultipartFile imagen) throws IOException {
		Optional<Obra> obraOpt = obraRepository.findByNombre(obraDto.getNombre());

		if (obraOpt.isPresent()) {
			throw new RuntimeException("Obra already exists");
		}

		Tipo tipo = tipoRepository.findById(obraDto.getTipoId())
				.orElseThrow(() -> new RuntimeException("'Tipo' does not exist"));
		Estado estado = estadoRepository.findById(obraDto.getEstadoId())
				.orElseThrow(() -> new RuntimeException("'Estado' does not exist"));
		Demografia demografia = demografiaRepository.findById(obraDto.getDemografiaId())
				.orElseThrow(() -> new RuntimeException("'Demografia' does not exist"));

		String location = normalizeString(obraDto.getNombre());
		
		String imagePath = fileService.storeFile(imagen, location);

		Obra obra = Obra.builder().nombre(obraDto.getNombre()).fechaLanzamiento(obraDto.getFechaLanzamiento())
				.descripcion(obraDto.getDescripcion()).tipo(tipo).estado(estado).imagen(imagePath)
				.demografia(demografia).build();

		return obraRepository.save(obra);
	}

	public List<Obra> listObras() {
		return obraRepository.findAll();
	}

	public Obra getObra(Integer id) {

		Optional<Obra> obraOpt = obraRepository.findById(id);
		if (obraOpt.isEmpty()) {
			return null;
		}

		return obraOpt.get();
	}
	
	public static String normalizeString(String string) {
	    return string.toLowerCase().replaceAll("\\s+", "");
	}


}
