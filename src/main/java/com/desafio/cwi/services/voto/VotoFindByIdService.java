package com.desafio.cwi.services.voto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;

@Service
public class VotoFindByIdService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	public Voto findById(Long id) throws Exception {
		Voto votoExists = findVotoById(id);
		
		return votoExists;
		
	}
	
	public Voto findVotoById(Long id) throws Exception {
		Voto voto = votoRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Voto.class.getName()));
		return voto;
	}
	

}
