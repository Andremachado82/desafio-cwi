package com.desafio.cwi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaGetAllService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public List<Pauta> getAllPauta() {
		List<Pauta> listPauta = pautaRepository.findAll();
		
		return listPauta;
	}

}
