package com.desafio.cwi.services.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoDeleteService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	public void deleteById(Long id) throws Exception {
		findSessaoById(id);
		sessaoVotacaoRepository.deleteById(id);		
	}
	
	public SessaoVotacao findSessaoById(Long id) throws Exception {
		return sessaoVotacaoRepository.findById(id).orElseThrow(() -> new Exception(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + SessaoVotacao.class.getName()));
	}

}
