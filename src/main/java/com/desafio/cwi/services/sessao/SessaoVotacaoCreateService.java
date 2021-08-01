package com.desafio.cwi.services.sessao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;
import com.desafio.cwi.services.exceptions.ApiGenericException;

@Service
public class SessaoVotacaoCreateService {
	
	@Autowired
	private SessaoVotacaoRepository sessaoVotacaoRepository;
	
	@Autowired
	PautaRepository pautaRepository;
	
	public SessaoVotacao create(Long idPauta, SessaoVotacao sessao) {
		if (sessao.getDataHoraInicio() == null ) {
			throw new ApiGenericException("Data não pode ser nula");
		}
		Optional<Pauta> pauta = pautaRepository.findById(idPauta);
		if (!pauta.isPresent()) {
			throw new ApiGenericException("Pauta não encontrada com ID: " + idPauta + ", Tipo: " + Pauta.class.getName());
		}
		sessao.setPauta(pauta.get());
		return sessaoVotacaoRepository.save(sessao);
	}
}
