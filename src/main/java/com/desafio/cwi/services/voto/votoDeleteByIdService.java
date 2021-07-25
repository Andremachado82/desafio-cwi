package com.desafio.cwi.services.voto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;

@Service
public class votoDeleteByIdService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	public void deleteById(Long id) throws Exception {
		findVotoById(id);
		votoRepository.deleteById(id);		
	}
	
	public Voto findVotoById(Long id) throws Exception {
		return votoRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Voto.class.getName()));
	}

}
