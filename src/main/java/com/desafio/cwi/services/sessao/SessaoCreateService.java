package com.desafio.cwi.services.sessao;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.exceptions.InvalidDateException;
import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.services.pauta.PautaGetByIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessaoCreateService {
	
	@Autowired
	SessaoRepository sessaoVotacaoRepository;
	
	@Autowired
	PautaGetByIdService pautaGetByIdService;
	
	public Sessao execute(Sessao sessao) {
		validaDataHoraInicio(sessao);
		verificaTempoSessao(sessao);
		validaPautaExistente(sessao);
		sessao.setPauta(pautaGetByIdService.execute(sessao.getPauta().getId()));
		var sessaoSalva = sessaoVotacaoRepository.save(sessao);
		log.info("Sessão criada com sucesso!");
		return sessaoSalva;
	}
	
	public Sessao verificaTempoSessao(Sessao sessao) {
		if (sessao.getTempoSessao() != null && sessao.getTempoSessao() <= 0) {
			throw new ApiGenericException("Tempo não pode ser zero ou negativo");
		}
		if (sessao.getTempoSessao() == null) {
			sessao.setTempoSessao(1l);
		}
		return sessao;
	}
	
	public void validaPautaExistente(Sessao sessao) {
		if (sessao.getPauta() == null || sessao.getPauta().getId() == null) {
			throw new PautaNotFoundException("Uma Pauta deve ser informada!");
		}
	}
	
	public Sessao validaDataHoraInicio(Sessao sessao) {
		if (sessao.getDataHoraInicio() == null ) {
			sessao.setDataHoraInicio(LocalDateTime.now());
		} else if (sessao.getDataHoraInicio().isBefore(LocalDateTime.now())) {
			throw new InvalidDateException("A data não pode ser menor que a data atual!");
		}
		return sessao;
	}
}
