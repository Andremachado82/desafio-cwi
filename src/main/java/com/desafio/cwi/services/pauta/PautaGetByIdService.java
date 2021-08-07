package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaGetByIdService {
	
	@Autowired
	PautaRepository pautaRepository;
	
	public Pauta execute(Long id) {
		return pautaRepository.findById(id).orElseThrow(() -> new PautaNotFoundException(
				"Pauta não encontrada!"));		
	}
}
