package com.desafio.cwi.services.sessaoTestes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;
import com.desafio.cwi.services.exceptions.ApiGenericException;
import com.desafio.cwi.services.sessao.SessaoVotacaoCreateService;

@RunWith(MockitoJUnitRunner.class)
public class SessaoCreateServiceTest {

	@InjectMocks
	private SessaoVotacaoCreateService sessaoCreateService;
	
	@Mock
	private PautaRepository pautaRepository;
	
	@Mock
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroQuandoSalvarSessaoComDataNula() {

		SessaoVotacao sessao = getSessao();
		sessao.setDataHoraInicio(null);
		
		sessaoCreateService.create(getPauta().getId(), sessao);
		sessao.setPauta(getPauta());

		verify(sessaoVotacaoRepository, never()).save(any(SessaoVotacao.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroAoSalvarQuandoNaoExistirPauta() {
		
		SessaoVotacao sessao = getSessao();
		
		sessaoCreateService.create(getPauta().getId(), sessao);

		verify(sessaoVotacaoRepository, never()).save(any(SessaoVotacao.class));
	}
	
	public SessaoVotacao getSessao() {
		return SessaoVotacao.builder()
				.id(new Random()
				.nextLong()).dataHoraInicio(LocalDateTime.now())
				.pauta(null)
				.build();
	}
	public Pauta getPauta() {
		return Pauta.builder()
				.id(9l).name("Pauta 1")
				.description("Descrição")
				.build();
	}
}
