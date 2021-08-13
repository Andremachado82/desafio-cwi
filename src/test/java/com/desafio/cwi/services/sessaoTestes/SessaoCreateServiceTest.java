package com.desafio.cwi.services.sessaoTestes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.exceptions.InvalidDateException;
import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.services.pauta.PautaGetByIdService;
import com.desafio.cwi.services.sessao.SessaoCreateService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SessaoCreateServiceTest {
	final String NOME_PAUTA = "Pauta 1";
	final String DESCRICAO_PAUTA = "Descrição";

	@InjectMocks
	private SessaoCreateService sessaoCreateService;
	
	@Mock
	private PautaGetByIdService pautaGetByIdService;
	
	@Mock
	private PautaRepository pautaRepository;
	
	@Mock
	private SessaoRepository sessaoVotacaoRepository;

	@Test(expected = InvalidDateException.class)
	public void deveOcorrerErroQuandoSalvarSessaoComDataAntesDaDataAtual() {

		Sessao sessao = getSessao();
		sessao.setDataHoraInicio(LocalDateTime.of(2018, 07, 22, 10, 15, 30));
		
		sessaoCreateService.execute(sessao);

		verify(sessaoVotacaoRepository, never()).save(any(Sessao.class));
	}
	
	@Test(expected = PautaNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoNaoExistirPauta() {
		
		Sessao sessao = getSessao();
		sessao.setPauta(null);
		
		sessaoCreateService.execute(sessao);

		verify(sessaoVotacaoRepository, never()).save(any(Sessao.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroAoSalvarQuandoTempoSessaoForMenorQueUm() {
		
		Sessao sessao = getSessao();
		sessao.setTempoSessao(-1l);
		
		sessaoCreateService.execute(sessao);
		
		verify(sessaoVotacaoRepository, never()).save(any(Sessao.class));
	}
	
	@Test
	public void deveSalvarSessaoComTempoSessaoPadraoQuandoTempoForNulo() {
		
		Sessao sessao = getSessao();
		sessao.setTempoSessao(null);
		
		sessaoCreateService.execute(sessao);
		sessao.setPauta(getPauta());
		
		when(pautaGetByIdService.execute(sessao.getPauta().getId())).thenReturn(getPauta());
		
		Optional<Pauta> pautaSalva = Optional.ofNullable(new Pauta());
		when(pautaRepository.findById(sessao.getPauta().getId())).thenReturn(pautaSalva);
		
		verify(sessaoVotacaoRepository).save(any(Sessao.class));
	}
	
	public Sessao getSessao() {
		return Sessao.builder()
				.id(new Random()
				.nextLong()).dataHoraInicio(null)
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
