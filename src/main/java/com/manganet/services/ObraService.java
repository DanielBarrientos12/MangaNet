package com.manganet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manganet.entities.Obra;
import com.manganet.repositories.ObraRepository;

@Service
public class ObraService {
	
	@Autowired
	public ObraRepository obraRepository;
	
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


}
