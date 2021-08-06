package com.desafio.cwi.services.voto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.client.CpfValidationClient;
import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.responses.CpfValidationResponse;

@Service
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
		Pauta pauta = pautaRepository.findById(voto.getPauta().getId()).orElseThrow(() -> new ApiGenericException("Pauta não encontrada!"));
		Sessao sessao = sessaoRepository.findById(idSessao).orElseThrow(() -> new ApiGenericException("Sessão não encontrada!"));
		if (sessao.getPauta().getId().equals(pauta.getId())) {
			throw new ApiGenericException("Sessão inválida!");
		}
		voto.setPauta(pauta);		
		return votoRepository.save(verificaVotoAndSalva(sessao, voto));
	}
	
	private Voto verificaVotoAndSalva(Sessao sessao, Voto voto) {
		return verificaVoto(sessao, voto);		 
	}

	private Voto verificaVoto(Sessao sessao, Voto voto) {
		if (voto.getEscolha() == null) {
			throw new ApiGenericException("Escolha do voto não pode ser nula");
		}
		LocalDateTime dataLimite = sessao.getDataHoraInicio().plusMinutes(sessao.getTempoSessao());
		if (LocalDateTime.now().isAfter(dataLimite)) {
			throw new ApiGenericException("Sessão expirada");
		}
		cpfAbleToVote(voto);
		verificaCpfExsiteNaPauta(voto);
		
		return voto;
	}
	
	private void verificaCpfExsiteNaPauta(Voto voto) {
		Optional<Voto> votoExistente = votoRepository.findByCpfAndPautaId(voto.getCpf(), voto.getPauta().getId());
		if (votoExistente.isPresent()) {
			throw new ApiGenericException("Já existe um voto registrado nesta Pauta de nº " + voto.getPauta().getId() + " com o CPF " + voto.getCpf());
		}
	}

	protected void cpfAbleToVote(final Voto voto) {
		String cpfFormatado = formataCpf(voto.getCpf());
		CpfValidationResponse cpfResponse = cpfValidationClient.findUserByCpf(cpfFormatado);
		if (cpfResponse.getStatus().equals(CPF_UNABLE_TO_VOTE)) {
			throw new ApiGenericException("CPF inválido para votação");
		} 
	}

	private String formataCpf(String cpf) {
		return cpf.replaceAll("[^0-9]+", "");
	}
}
