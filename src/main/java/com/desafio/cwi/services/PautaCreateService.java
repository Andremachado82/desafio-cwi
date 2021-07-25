package com.desafio.cwi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaCreateService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta create(Pauta pauta) {
		
		return pautaRepository.save(pauta);
	}
	

}
