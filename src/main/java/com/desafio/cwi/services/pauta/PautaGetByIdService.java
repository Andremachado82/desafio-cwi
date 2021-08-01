package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.services.exceptions.ApiGenericException;

@Service
public class PautaGetByIdService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta getById(Long id) {
		
		return findPautaById(id);		
	}
	
	public Pauta findPautaById(Long id) {
		Pauta pauta = pautaRepository.findById(id).orElseThrow(() -> new ApiGenericException(
				"Pauta não encontrada com ID: " + id + ", Tipo: " + Pauta.class.getName()));
		return pauta;
	}

}
