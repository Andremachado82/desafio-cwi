package com.desafio.cwi.services.VotoTestes;

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
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.services.exceptions.ObjectNotFoundException;
import com.desafio.cwi.services.voto.VotoCreateService;

@RunWith(MockitoJUnitRunner.class)
public class VotoCreateServiceTest {

	@InjectMocks
	private VotoCreateService votoCreateService;

	@Mock
	private VotoRepository votoRepository;

	@Test
	public void deveSalvarUmVoto() {
		Voto voto = getVoto();
		SessaoVotacao sessao = getSessao();
		Pauta pauta = getPauta();
		
//		sessao.setDataHoraInicio(LocalDateTime.now().plusMinutes(1));

		votoCreateService.create(pauta.getId(), sessao.getId(), voto);

		verify(votoRepository).save(any(Voto.class));
	}

//	@Test
//	public void deveOcorrerErroQuandoSalvarVotoSemEscolha() {
//
//		Voto voto = getVoto();
//		SessaoVotacao sessao = getSessao();
//		Pauta pauta = getPauta();
//		
//
//		votoCreateService.create(pauta.getId(), sessao.getId(), voto);
//
//		verify(votoRepository, never()).save(any(Voto.class));
//	}

	public Voto getVoto() {
		return Voto.builder()
					.id(new Random()
						.nextLong())
					.cpf("18788792005")
					.escolha(true)					
					.build();
	}
	
	public SessaoVotacao getSessao() {
		return SessaoVotacao.builder()
				.id(1l)
				.dataHoraInicio(LocalDateTime.now().plusMinutes(-1l))
				.tempoSessao(1l)
				.pauta(getPauta())
				.build();
	}
	
	public Pauta getPauta() {
		return Pauta.builder()
				.id(1l)
				.name("Pauta 1")
				.description("Descrição")
				.build();
	}
}
