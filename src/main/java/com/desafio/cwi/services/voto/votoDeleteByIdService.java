package com.desafio.cwi.services.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;

@Service
public class votoDeleteByIdService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	public void deleteById(Long id) {
		findVotoById(id);
		votoRepository.deleteById(id);		
	}
	
	public Voto findVotoById(Long id) {
		return votoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Voto não encontrado com o ID: " + id + ", Tipo: " + Voto.class.getName()));
	}

}
