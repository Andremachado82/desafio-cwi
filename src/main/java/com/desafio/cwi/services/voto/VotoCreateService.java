package com.desafio.cwi.services.voto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.client.CpfValidationClient;
import com.desafio.cwi.enums.RespostaEnum;
import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.exceptions.CpfNotFoundException;
import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.exceptions.SessaoNotFoundException;
import com.desafio.cwi.exceptions.SessionExperidException;
import com.desafio.cwi.exceptions.UnableCpfException;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.utils.FormataCPF;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class VotoCreateService {

	private static final String CPF_UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

	@Autowired
	VotoRepository votoRepository;

	@Autowired
	PautaRepository pautaRepository;

	@Autowired
	SessaoRepository sessaoRepository;

	@Autowired
	CpfValidationClient cpfValidationClient;
	
	public Voto execute(Long idSessao, Voto voto) {
		if (voto.getPauta() != null) {
			voto.setPauta(pautaRepository.findById(voto.getPauta().getId())
					.orElseThrow(() -> new PautaNotFoundException("Pauta não encontrada!")));
		}
		var sessao = new Sessao();
		if (idSessao != null) {
			sessao = sessaoRepository.findById(idSessao)
					.orElseThrow(() -> new SessaoNotFoundException("Sessão não encontrada!"));
		}
		verificaVoto(sessao, voto);
		votoRepository.save(voto);		
		log.info("Voto realizado com sucesso!");
		return voto;
	}

	private void verificaVoto(Sessao sessao, Voto voto) {
		if (voto.getResposta() == null) {
			throw new ApiGenericException("Resposta do voto não pode ser nula");
		}
		var dataLimite = sessao.getDataHoraInicio().plusMinutes(sessao.getTempoSessao());
		if (LocalDateTime.now().isAfter(dataLimite)) {
			throw new SessionExperidException("Sessão expirada");
		}
		cpfAbleToVote(voto.getCpf());
		verificaCpfExisteNaPauta(voto.getCpf(), voto.getPauta().getId());
	}

	private void verificaCpfExisteNaPauta(String cpf, Long idPauta) {
		Optional<Voto> votoExistente = votoRepository.findByCpfAndPautaId(cpf, idPauta);
		if (votoExistente.isPresent()) {
			throw new ApiGenericException("Já existe um voto registrado nesta Pauta de nº " + idPauta
					+ " com o CPF " + cpf);
		}
	}

	private void cpfAbleToVote(String cpf) {
		if (cpf == null || cpf.isBlank()) {
			throw new CpfNotFoundException("CPF deve ser informado!");
		}
		String cpfFormatado = FormataCPF.formataCpf(cpf);
		try {
			var cpfResponse = cpfValidationClient.findUserByCpf(cpfFormatado);
			if (CPF_UNABLE_TO_VOTE.equals(cpfResponse.getStatus())) {
				throw new UnableCpfException("CPF sem permissão para voto");
			}
		} catch (FeignException e) {
			throw new ApiGenericException("Ocorreu um erro, tente novamente mais tarde!" + e.getMessage());
		}
	}
	
	public Boolean verificaResposta(String resposta) {
		if (resposta.equals(RespostaEnum.SIM.getResposta())) {
			return true;
		} else if (resposta.equals(RespostaEnum.NAO.getResposta())) {
			return false;
		} else {
			throw new ApiGenericException("A resposta deve ser Sim ou Não");
		}
	}
}
