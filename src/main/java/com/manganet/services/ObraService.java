package com.manganet.services;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.nio.file.Path;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.manganet.dto.ObraDTO;
import com.manganet.dto.ResponseObra;
import com.manganet.entities.Demografia;
import com.manganet.entities.Estado;
import com.manganet.entities.Genero;
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
	public GeneroService generoService;
	
	@Autowired
    public FileService fileService;

	public Obra createObra(ObraDTO obraDto, MultipartFile imagen) throws IOException {
		
		//busco si existe sino da una excepcion
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

		
		//nomalizo el nombre y para volverlo el nombre de la carpeta donde se guardaran los archivos
		String location = normalizeString(obraDto.getNombre());
		String imagePath = fileService.storeFile(imagen, location);
		
		//busco los generos que se van a agregar
		List<Genero> generos = generoService.searchGenresById(obraDto.getGeneros());

		Obra obra = Obra.builder().nombre(obraDto.getNombre()).fechaLanzamiento(obraDto.getFechaLanzamiento())
				.descripcion(obraDto.getDescripcion()).tipo(tipo).estado(estado).imagen(imagePath)
				.demografia(demografia).generos(generos).build();

		return obraRepository.save(obra);
	}

	public List<ResponseObra> listObras() {
	    List<Obra> obras = obraRepository.findAll();
	    List<ResponseObra> responseObras = new ArrayList<>();

	    for (Obra obra : obras) {
	        Resource file = fileService.loadFile(obra.getImagen());
	        String base64Image = encodeFileToBase64(file);

	        ResponseObra obraDto = ResponseObra.builder()
	                .id(obra.getId())
	                .nombre(obra.getNombre())
	                .fechaLanzamiento(obra.getFechaLanzamiento())
	                .descripcion(obra.getDescripcion())
	                .tipo(obra.getTipo())
	                .estado(obra.getEstado())
	                .imagen(base64Image) // Agrega la imagen convertida a Base64
	                .likes(obra.getLikes())
	                .dislikes(obra.getDislikes())
	                .demografia(obra.getDemografia())
	                .usuarios(obra.getUsuarios())
	                .generos(obra.getGeneros())
	                .build();

	        responseObras.add(obraDto);
	    }

	    return responseObras;
	}


	public ResponseObra getObra(Integer id) {

		Optional<Obra> obraOpt = obraRepository.findById(id);
		if (obraOpt.isEmpty()) {
			return null;
		}
		
		Obra obra = obraOpt.get();
		
		Resource file = fileService.loadFile(obra.getImagen());
        String base64Image = encodeFileToBase64(file);

		
        ResponseObra obraDto = ResponseObra.builder()
                .id(obra.getId())
                .nombre(obra.getNombre())
                .fechaLanzamiento(obra.getFechaLanzamiento())
                .descripcion(obra.getDescripcion())
                .tipo(obra.getTipo())
                .estado(obra.getEstado())
                .imagen(base64Image) // Agrega la imagen convertida a Base64
                .likes(obra.getLikes())
                .dislikes(obra.getDislikes())
                .demografia(obra.getDemografia())
                .usuarios(obra.getUsuarios())
                .generos(obra.getGeneros())
                .build();


		return obraDto;
	}
	
	public static String normalizeString(String string) {
	    return string.toLowerCase().replaceAll("\\s+", "");
	}

	private String encodeFileToBase64(Resource resource) {
	    try {
	        byte[] fileContent = Files.readAllBytes(Path.of(resource.getURI()));
	        return Base64.getEncoder().encodeToString(fileContent);
	    } catch (IOException e) {
	        throw new RuntimeException("Error converting file to Base64", e);
	    }
	}
	
}
