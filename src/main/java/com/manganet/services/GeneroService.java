package com.manganet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manganet.entities.Genero;
import com.manganet.repositories.GeneroRepository;

@Service
public class GeneroService {
	
	@Autowired
	GeneroRepository generoRepository;
	
	public List<Genero> searchGenresById(List<Integer> ids) {
	    List<Genero> generos = new ArrayList<Genero>();

	    for (Integer iterador : ids) {
	        generos.add(generoRepository.findById(iterador).orElse(null));
	    }

	    return generos;
	}


}
