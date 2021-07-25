package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaGetByIdService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta getById(Long id) throws Exception {
		
		return findPautaById(id);		
	}
	
	public Pauta findPautaById(Long id) throws Exception {
		Pauta pauta = pautaRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pauta.class.getName()));
		return pauta;
	}

}
