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

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.services.sessao.SessaoCreateService;

@RunWith(MockitoJUnitRunner.class)
public class SessaoCreateServiceTest {

	@InjectMocks
	private SessaoCreateService sessaoCreateService;
	
	@Mock
	private PautaRepository pautaRepository;
	
	@Mock
	private SessaoRepository sessaoVotacaoRepository;

	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroQuandoSalvarSessaoComDataNula() {

		Sessao sessao = getSessao();
		sessao.setDataHoraInicio(null);
		
		sessaoCreateService.execute(sessao);
		sessao.setPauta(getPauta());

		verify(sessaoVotacaoRepository, never()).save(any(Sessao.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroAoSalvarQuandoNaoExistirPauta() {
		
		Sessao sessao = getSessao();
		
		sessaoCreateService.execute(sessao);

		verify(sessaoVotacaoRepository, never()).save(any(Sessao.class));
	}
	
	public Sessao getSessao() {
		return Sessao.builder()
				.id(new Random()
				.nextLong()).dataHoraInicio(LocalDateTime.now())
				.pauta(getPauta())
				.build();
	}
	public Pauta getPauta() {
		return Pauta.builder()
				.id(9l).name("Pauta 1")
				.description("Descrição")
				.build();
	}
}
