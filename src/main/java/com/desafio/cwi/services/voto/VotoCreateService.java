package com.desafio.cwi.services.voto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.client.CpfValidationClient;
import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.responses.CpfValidationResponse;
import com.desafio.cwi.services.sessao.SessaoVotacaoGetByIdService;

@Service
public class VotoCreateService {

	private final String CPF_UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
	
	@Autowired
	VotoRepository votoRepository;

	@Autowired
	SessaoVotacaoGetByIdService sessaoVotacaoGetByIdService;
	
	@Autowired
	CpfValidationClient cpfValidationClient;	
	

	public Voto create(Long idPauta, Long idSessao, Voto voto) {
		var sessao = sessaoVotacaoGetByIdService.findByIdAndPautaId(idSessao, idPauta);
		if (!idPauta.equals(sessao.getPauta().getId())) {
			throw new ApiGenericException("Sessão inválida");
		}
		voto.setPauta(sessao.getPauta());		
		return verificaVotoAndSalva(sessao, voto);
	}
	
	public Voto verificaVotoAndSalva(final SessaoVotacao sessao, final Voto voto) {
		verificaVoto(sessao, voto);
		return votoRepository.save(voto);
	}

	protected void verificaVoto(final SessaoVotacao sessao, final Voto voto) {
		if (voto.getEscolha() == null) {
			throw new ApiGenericException("Escolha do voto não pode ser nula");
		}
		LocalDateTime dataLimite = sessao.getDataHoraInicio().plusMinutes(sessao.getTempoSessao());
		if (LocalDateTime.now().isAfter(dataLimite)) {
			throw new ApiGenericException("Sessão expirada");
		}
		cpfAbleToVote(voto);
		verificaCpfExsiteNaPauta(voto);
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

	public List<Voto> findAll() {
		return votoRepository.findAll();
	}
}
