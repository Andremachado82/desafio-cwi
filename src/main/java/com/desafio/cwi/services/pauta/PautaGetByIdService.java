package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaGetByIdService {
	
	@Autowired
	PautaRepository pautaRepository;
	
	public Pauta findById(Long id) {
		return findPautaById(id);		
	}
	
	public Pauta findPautaById(Long id) {
		var pauta = pautaRepository.findById(id).orElseThrow(() -> new ApiGenericException(
				"Pauta não encontrada com ID: " + id + ", Tipo: " + Pauta.class.getName()));
		return pauta;
	}
}
