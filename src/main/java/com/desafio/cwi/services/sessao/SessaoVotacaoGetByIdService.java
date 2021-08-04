package com.desafio.cwi.services.sessao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoGetByIdService {
	
	@Autowired
	SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public SessaoVotacao findById(Long id) {
		var sessaoExists = findSessaoById(id);
		return sessaoExists;
	}
	
	public SessaoVotacao findSessaoById(Long id) {
		var sessao = sessaoVotacaoRepository.findById(id).orElseThrow(() -> new ApiGenericException(
				"Sessão não encontrado com o ID: " + id + ", Tipo: " + SessaoVotacao.class.getName()));
		return sessao;
	}
	
	public SessaoVotacao findByIdAndPautaId(Long idSessao, Long idPauta) {
		Optional<SessaoVotacao> sessao = sessaoVotacaoRepository.findByIdAndPautaId(idSessao, idPauta);		
		if (!sessao.isPresent()) {
			throw new ApiGenericException("Sessão não encontrado com o ID:" + idSessao + ", Tipo: " + SessaoVotacao.class.getName());
		}
		return sessao.get();
	}
}
