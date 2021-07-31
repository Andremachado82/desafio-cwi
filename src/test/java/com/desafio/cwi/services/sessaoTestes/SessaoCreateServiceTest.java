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

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.repositories.SessaoVotacaoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;
import com.desafio.cwi.services.pauta.PautaCreateService;
import com.desafio.cwi.services.sessao.SessaoVotacaoCreateService;

@RunWith(MockitoJUnitRunner.class)
public class SessaoCreateServiceTest {

	@InjectMocks
	private SessaoVotacaoCreateService sessaoCreateService;
	
	@InjectMocks
	private PautaCreateService pautaCreateService;
	
	@Mock
	private SessaoVotacaoRepository sessaoVotacaoRepository;

	@Test(expected = ObjectNotFoundException.class)
	public void deveOcorrerErroQuandoSalvarSessaoComDataNula() {

		SessaoVotacao sessao = getSessao();
		sessao.setDataHoraInicio(null);
		
		sessaoCreateService.create(1l, sessao);

		verify(sessaoVotacaoRepository, never()).save(any(SessaoVotacao.class));
	}
	
	public SessaoVotacao getSessao() {
		return SessaoVotacao.builder()
				.id(new Random()
				.nextLong()).dataHoraInicio(LocalDateTime.now())
				.build();
	}
}
