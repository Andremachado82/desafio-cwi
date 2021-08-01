package com.desafio.cwi.services.voto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;

@Service
public class VotoFindAllService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	public List<Voto> findAll() {
		List<Voto> listVoto = votoRepository.findAll();
		
		return listVoto;
	}
}
