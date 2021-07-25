package com.desafio.cwi.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaUpdateService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta update(Long id, Pauta pauta) throws Exception {
		Pauta pautaExists = findPautaById(id);
		
		BeanUtils.copyProperties(pauta, pautaExists, "id");			
		
		return pautaRepository.save(pauta);		
	}
	
	public Pauta findPautaById(Long id) throws Exception {
		Pauta pauta = pautaRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pauta.class.getName()));
		return pauta;
	}

}
