package com.desafio.cwi.services.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;

@Service
public class SessaoVotacaoDeleteService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public void deleteById(Long id) {
		findSessaoById(id);
		sessaoVotacaoRepository.deleteById(id);		
	}
	
	public SessaoVotacao findSessaoById(Long id) {
		return sessaoVotacaoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Sessão não encontrada com o ID: " + id + ", Tipo: " + SessaoVotacao.class.getName()));
	}

}
