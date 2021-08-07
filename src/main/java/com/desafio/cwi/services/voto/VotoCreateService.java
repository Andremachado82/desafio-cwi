package com.desafio.cwi.services.voto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.client.CpfValidationClient;
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
import com.desafio.cwi.responses.CpfValidationResponse;
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
		Sessao sessao = new Sessao();
		if (idSessao != null) {
			sessao = sessaoRepository.findById(idSessao)
					.orElseThrow(() -> new SessaoNotFoundException("Sessão não encontrada!"));
		}
		votoRepository.save(verificaVoto(sessao, voto));		
		log.info("Voto realizado com sucesso!");
		return voto;
	}

	private Voto verificaVoto(Sessao sessao, Voto voto) {
		if (voto.getResposta() == null) {
			throw new ApiGenericException("Resposta do voto não pode ser nula");
		}
		LocalDateTime dataLimite = sessao.getDataHoraInicio().plusMinutes(sessao.getTempoSessao());
		if (LocalDateTime.now().isAfter(dataLimite)) {
			throw new SessionExperidException("Sessão expirada");
		}
		cpfAbleToVote(voto);
		verificaCpfExsiteNaPauta(voto);
		return voto;
	}

	private void verificaCpfExsiteNaPauta(Voto voto) {
		Optional<Voto> votoExistente = votoRepository.findByCpfAndPautaId(voto.getCpf(), voto.getPauta().getId());
		if (votoExistente.isPresent()) {
			throw new ApiGenericException("Já existe um voto registrado nesta Pauta de nº " + voto.getPauta().getId()
					+ " com o CPF " + voto.getCpf());
		}
	}

	protected void cpfAbleToVote(final Voto voto) {
		if (voto.getCpf() == null || voto.getCpf().isBlank()) {
			throw new CpfNotFoundException("CPF deve ser informado!");
		}
		String cpfFormatado = FormataCPF.formataCpf(voto.getCpf());
		try {
			CpfValidationResponse cpfResponse = cpfValidationClient.findUserByCpf(cpfFormatado);
			if (cpfResponse.getStatus().equals(CPF_UNABLE_TO_VOTE)) {
				throw new UnableCpfException("CPF sem permissão para voto");
			}
		} catch (FeignException e) {
			throw new ApiGenericException("Ocorreu um erro, tente novamente mais tarde!" + e.getMessage());
		}
	}
}
