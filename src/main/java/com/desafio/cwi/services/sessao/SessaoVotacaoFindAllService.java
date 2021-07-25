package com.desafio.cwi.services.sessao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoFindAllService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public List<SessaoVotacao> findAll() {
		List<SessaoVotacao> listSessao = sessaoVotacaoRepository.findAll();
		return listSessao;
	}
	

}
