package com.desafio.cwi.services.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.services.exceptions.ApiGenericException;

@Service
public class VotoFindByIdService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	public Voto findById(Long id) {
		Voto votoExists = findVotoById(id);
		
		return votoExists;
		
	}
	
	public Voto findVotoById(Long id) {
		Voto voto = votoRepository.findById(id).orElseThrow(() -> new ApiGenericException(
				"Voto não encontrado com o ID: " + id + ", Tipo: " + Voto.class.getName()));
		return voto;
	}
	

}
