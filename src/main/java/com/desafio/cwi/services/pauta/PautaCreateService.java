package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class PautaCreateService {
	
	@Autowired
	PautaRepository pautaRepository;

	public Pauta execute(Pauta pauta) {
		if (pauta.getName() == null || pauta.getName().isBlank()) {
			throw new ApiGenericException("Nome da pauta não deve estar em branco ou nula!");
		}
		if (pauta.getName().length() < 2) {
			throw new ApiGenericException("Nome da pauta deve conter mais de duas letras!");
		}
		var existePauta = pautaRepository.findByName(pauta.getName());
		
		if (existePauta != null) {
			throw new ApiGenericException("Nome da pauta existente!");
		}
		Pauta pautaSalva = pautaRepository.save(pauta);
		log.info("Pauta criada com sucesso!");
		return pautaSalva;
	}
}
