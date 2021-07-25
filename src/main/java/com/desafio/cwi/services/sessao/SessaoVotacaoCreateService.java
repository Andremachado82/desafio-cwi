package com.desafio.cwi.services.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoCreateService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public SessaoVotacao create(SessaoVotacao sessao) {
		
		return sessaoVotacaoRepository.save(sessao);
	}
	

}
