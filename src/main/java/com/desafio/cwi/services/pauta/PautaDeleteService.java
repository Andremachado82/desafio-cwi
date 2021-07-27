package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;

@Service
public class PautaDeleteService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public void deleteById(Long id) {
		findPautaById(id);
		pautaRepository.deleteById(id);		
	}
	
	public Pauta findPautaById(Long id) {
		return pautaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException (
				"Pauta de ID: " + id + ", Tipo: " + Pauta.class.getName()));
	}

}
