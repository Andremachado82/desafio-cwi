package com.desafio.cwi.services.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;

@Service
public class SessaoVotacaoGetByIdService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public SessaoVotacao findById(Long id) {
		SessaoVotacao sessaoExists = findSessaoById(id);
		
		return sessaoExists;
	}
	
	public SessaoVotacao findSessaoById(Long id) {
		SessaoVotacao sessao = sessaoVotacaoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Sessão não encontrado com o ID: " + id + ", Tipo: " + SessaoVotacao.class.getName()));
		return sessao;
	}
	

}
