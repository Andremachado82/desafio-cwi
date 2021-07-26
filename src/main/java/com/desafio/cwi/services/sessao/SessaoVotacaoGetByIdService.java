package com.desafio.cwi.services.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoGetByIdService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public SessaoVotacao findById(Long id) throws Exception {
		SessaoVotacao sessaoExists = findSessaoById(id);
		
		return sessaoExists;
	}
	
	public SessaoVotacao findSessaoById(Long id) throws Exception {
		SessaoVotacao sessao = sessaoVotacaoRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + SessaoVotacao.class.getName()));
		return sessao;
	}
	

}
