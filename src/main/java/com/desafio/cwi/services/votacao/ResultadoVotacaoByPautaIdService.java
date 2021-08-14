package com.desafio.cwi.services.votacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.dtos.ResultadoVotacaoDto;
import com.desafio.cwi.exceptions.VotacaoNotFoundException;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.v2.services.RabbitMQService;

@Service
public class ResultadoVotacaoByPautaIdService {
	public static final String RESULTADO_VOTACAO = "resultadoVotacao";
	
	@Autowired
	VotoRepository votoRepository;
	
	@Autowired
	SessaoRepository sessaoRepository;	
	
	@Autowired
	RabbitMQService rabbitMQService;
	
	public ResultadoVotacaoDto getResultadoVotacao(Long idPauta){
		ResultadoVotacaoDto votacaoPauta = montaDtoVotacao(idPauta);	
		rabbitMQService.enviaResultadoVotacao(RESULTADO_VOTACAO, votacaoPauta);
		return votacaoPauta;
	}	

	public ResultadoVotacaoDto montaDtoVotacao(Long idPauta) {
		 List<Voto> votosByPauta = votoRepository.findByPautaId(idPauta)
				    .orElseThrow(() -> new VotacaoNotFoundException("Voto não encontrado!"));
		var pauta = votosByPauta.iterator().next().getPauta();
		Integer total = votosByPauta.size();
		Integer totalSim = calculaTotalVotosSim(votosByPauta);
		Integer totalNao = calculaTotalVotosNao(total, totalSim);
		return ResultadoVotacaoDto.builder()
				.pauta(pauta)
				.totalVotos(total)
				.totalSim(totalSim).totalNao(totalNao)
				.build();
	}
	
	public Integer calculaTotalVotosNao(Integer total, Integer totalSim) {
		return total - totalSim;
	}
	
	public Integer calculaTotalVotosSim(List<Voto> votosByPauta) {
		return (int) votosByPauta.stream()
		.filter(voto -> Boolean.TRUE.equals(voto.getResposta()))
		.count();
	}
}
