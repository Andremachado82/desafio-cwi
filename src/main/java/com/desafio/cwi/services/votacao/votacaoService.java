package com.desafio.cwi.services.votacao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.dtos.VotacaoDto;
import com.desafio.cwi.exceptions.VotacaoNotFoundException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.repositories.VotoRepository;

@Service
public class votacaoService {
	
	@Autowired
	VotoRepository votoRepository;
	
	@Autowired
	SessaoRepository sessaoRepository;
	
	public VotacaoDto getResultVotacao(Long idPauta){
		VotacaoDto votacaoPauta = montaDtoVotacao(idPauta);
		return votacaoPauta;
	}
	
	public VotacaoDto montaDtoVotacao(Long idPauta) {
		Optional<List<Voto>> votosByPauta = votoRepository.findByPautaId(idPauta);
		if (votosByPauta == null || votosByPauta.isEmpty()) {
			throw new VotacaoNotFoundException("Voto não encontrado!");
		}
		Pauta pauta = votosByPauta.get().iterator().next().getPauta();
		Integer total = votosByPauta.get().size();
		Integer totalSim = (int) votosByPauta.get().stream()
				.filter(voto -> Boolean.TRUE.equals(voto.getResposta()))
				.count();
		Integer totalNao = total - totalSim;
		return VotacaoDto.builder()
				.pauta(pauta)
				.totalVotos(total)
				.totalSim(totalSim).totalNao(totalNao)
				.build();
	}
}
