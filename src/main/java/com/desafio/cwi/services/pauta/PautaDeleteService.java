package com.desafio.cwi.services.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;
import com.desafio.cwi.services.sessao.SessaoVotacaoDeleteService;
import com.desafio.cwi.services.voto.votoDeleteByIdService;

@Service
public class PautaDeleteService {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private SessaoVotacaoDeleteService sessaoVotacaoDeleteService;
	
	@Autowired
	private votoDeleteByIdService votoDeleteByIdService;
	
	
	
	public void deleteById(Long id) {
		findPautaById(id);
		sessaoVotacaoDeleteService.deleteSessaoByIdPauta(id);
		votoDeleteByIdService.deleteVotoByPautaId(id);
		pautaRepository.deleteById(id);	
	}
	
	public Pauta findPautaById(Long id) {
		return pautaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException (
				"Pauta não encontrada com o ID: " + id + ", Tipo: " + Pauta.class.getName()));
	}

}
