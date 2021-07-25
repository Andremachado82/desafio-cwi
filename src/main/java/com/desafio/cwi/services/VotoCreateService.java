package com.desafio.cwi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.VotoRepository;

@Service
public class VotoCreateService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	public Voto create(Voto voto) {
		
		return votoRepository.save(voto);
	}
	

}
