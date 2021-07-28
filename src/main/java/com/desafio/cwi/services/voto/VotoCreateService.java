package com.desafio.cwi.services.voto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.recursos.CpfValidationClient;
import com.desafio.cwi.recursos.CpfValidationResponse;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;
import com.desafio.cwi.services.sessao.SessaoVotacaoGetByIdService;

@Service
public class VotoCreateService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	SessaoVotacaoGetByIdService sessaoVotacaoGetByIdService;
	
	@Autowired
	CpfValidationClient cpfValidationClient;	

	public Voto create(Long idPauta, Long idSessao, Voto voto) {
		SessaoVotacao sessao = sessaoVotacaoGetByIdService.findByIdAndPautaId(idSessao, idPauta);
		if (!idPauta.equals(sessao.getPauta().getId())) {
			throw new ObjectNotFoundException("Sessão inválida");
		}

		voto.setPauta(sessao.getPauta());		
		return verificaVotoAndSalva(sessao, voto);
	}
	
	private Voto verificaVotoAndSalva(final SessaoVotacao sessao, final Voto voto) {
		verificaVoto(sessao, voto);
		return votoRepository.save(voto);
	}

	protected void verificaVoto(final SessaoVotacao sessao, final Voto voto) {

		LocalDateTime dataLimite = sessao.getDataHoraInicio().plusMinutes(sessao.getTempoSessao());
		if (LocalDateTime.now().isAfter(dataLimite)) {
			throw new ObjectNotFoundException("Sessão expirada");
		}

		cpfAbleToVote(voto);
	}
	
	protected void cpfAbleToVote(final Voto voto) {
		try {
			CpfValidationResponse cpfResponse = cpfValidationClient.getCpf(voto.getCpf());
			if (cpfResponse != null) {
				voto.setCpf(cpfResponse.getStatus());
			} else {
				throw new ObjectNotFoundException("O CPF informado não encontrado");
			}
		} catch (Exception e) {
			throw new ObjectNotFoundException("O CPF informado não é válido");
		}
		
	}

	public List<Voto> findAll() {
		return votoRepository.findAll();
	}
}
