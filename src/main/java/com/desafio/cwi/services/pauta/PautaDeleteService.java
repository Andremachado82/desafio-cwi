package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaDeleteService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public void deleteById(Long id) throws Exception {
		findPautaById(id);
		pautaRepository.deleteById(id);		
	}
	
	public Pauta findPautaById(Long id) throws Exception {
		return pautaRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pauta.class.getName()));
	}

}
