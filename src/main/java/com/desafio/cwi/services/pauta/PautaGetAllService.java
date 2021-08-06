package com.desafio.cwi.services.pauta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

@Service
public class PautaGetAllService {
	
	@Autowired
	PautaRepository pautaRepository;
	
	public List<Pauta> execute() {
		return pautaRepository.findAll();
	}
}
