package com.manganet.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.manganet.dto.CapituloDTO;
import com.manganet.entities.Capitulo;
import com.manganet.entities.Obra;
import com.manganet.entities.Usuario;
import com.manganet.repositories.CapituloRepository;

@Service
public class CapituloService {

	@Autowired
	CapituloRepository capituloRepository;

	@Autowired
	ObraService obraService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	public FileService fileService;

	public Capitulo crearCapitulo(CapituloDTO capituloDTO, List<MultipartFile> imagenes) throws IOException {

		Obra obra = obraService.getObra(capituloDTO.getObraId());

		if (obra == null) {
			new RuntimeException("Obra doesn't exists");
		}

		Usuario usuario = usuarioService.getUsuario(capituloDTO.getUsuarioId());

		String location = normalizeString(obra.getNombre()) + "/capitulo" + capituloDTO.getNumero();
		List<String> imgPaths = fileService.storeMultipleFiles(imagenes, location);

		Capitulo capitulo = Capitulo.builder().numero(capituloDTO.getNumero()).titulo(capituloDTO.getTitulo())
				.fechaPublicacion(capituloDTO.obtenerFechaPublicacion()).obra(obra).usuario(usuario)
				.imagenes(imgPaths.toArray(new String[0])).build();

		return capituloRepository.save(capitulo);
	}

	public static String normalizeString(String string) {
		return string.toLowerCase().replaceAll("\\s+", "");
	}

	public List<Capitulo> listCapitulosObra(Integer id) {
		return capituloRepository.findByObraId(id);
	}
	
	public Capitulo getCapituloByObraAndNumero(Integer obraId, Double numero) {
        Optional<Capitulo> capituloOpt = capituloRepository.findByObraIdAndNumero(obraId, numero);
        
        if (capituloOpt.isEmpty()) {
            throw new RuntimeException("Capítulo no encontrado para la obra ID " + obraId + " y número de capítulo " + numero);
        }
        
        return capituloOpt.get();
    }

}
