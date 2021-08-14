package com.desafio.cwi.services.votacaoTestes;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.cwi.exceptions.VotacaoNotFoundException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.services.votacao.ResultadoVotacaoByPautaIdService;

@RunWith(MockitoJUnitRunner.class)
public class ResultadoVotacaoByPautaIdServiceTest {
	final String NOME_PAUTA = "Pauta 1";
	final String DESCRICAO_PAUTA = "Descrição";
	
	@InjectMocks
	private ResultadoVotacaoByPautaIdService resultadoVotacaoByPautaIdService;
	
	@Mock
	VotoRepository votoRepository;
	
	@Test(expected = VotacaoNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoSessaoNãoExistir() {
		
		Voto voto = getVoto();
		
		resultadoVotacaoByPautaIdService.getResultadoVotacao(voto.getPauta().getId());
		
		when(votoRepository.findByPautaId(voto.getPauta().getId())).thenReturn(Optional.empty());
		
		assertThrows(VotacaoNotFoundException.class, () -> resultadoVotacaoByPautaIdService.getResultadoVotacao(voto.getPauta().getId()));
	}
	
	public Voto getVoto() {
		return Voto.builder()
				.id(new Random()
				.nextLong())
				.pauta(getPauta())
				.build();
	}
	
	public Pauta getPauta() {
		return Pauta.builder()
				.id(9l).name(NOME_PAUTA)
				.description(DESCRICAO_PAUTA)
				.build();
	}
}
