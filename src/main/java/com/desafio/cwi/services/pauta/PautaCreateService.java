package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.services.exceptions.ApiGenericException;

@Service
public class PautaCreateService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta create(Pauta pauta) {
		
		if (pauta.getName() == null || pauta.getName().isBlank()) {
			throw new ApiGenericException("Nome da pauta não deve estar em branco ou nula");
		}
		
		if (pauta.getName().length() < 2) {
			throw new ApiGenericException("Nome da pauta deve conter mais de duas letras");
		}
		
		Pauta existePauta = pautaRepository.findByName(pauta.getName());
		
		if (existePauta != null) {
			throw new ApiGenericException("Nome da pauta existente");
		}
		
		return pautaRepository.save(pauta);
	}
	

}
