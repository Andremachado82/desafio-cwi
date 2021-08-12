package com.desafio.cwi.services.pautaTestes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.services.pauta.PautaCreateService;
import com.desafio.cwi.services.pauta.PautaGetByIdService;

@RunWith(MockitoJUnitRunner.class)
public class PautaCreateServiceTest {

	@InjectMocks
	private PautaCreateService pautaCreateService;
	
	@InjectMocks
	private PautaGetByIdService pautaGetByIdService;

	@Mock
	private PautaRepository pautaRepository;

	@Test
	void devecriarUmaPautaComSucesso() {

		Pauta pauta = getPauta();

		pautaCreateService.execute(pauta);

		verify(pautaRepository).save(any(Pauta.class));
	}
	
	@Test(expected = ApiGenericException.class)
	void deveRetornarUmErroQuandoSalvarPautaSemNome() {

		Pauta pauta = getPauta();
		pauta.setName("");
		pautaCreateService.execute(pauta);

		verify(pautaRepository, never()).save(any(Pauta.class));
	}
	
	@Test(expected = ApiGenericException.class)
	void deveRetornarUmErroQuandoSalvarPautaComNomeNulo() {

		Pauta pauta = getPauta();
		pauta.setName(null);
		pautaCreateService.execute(pauta);

		verify(pautaRepository, never()).save(any(Pauta.class));
	}
	
	@Test(expected = ApiGenericException.class)
	void deveRetornarUmErroQuandoSalvarPautaComNomeExistente() {
		Pauta pauta = getPauta();			
		
		when(pautaRepository.findByName(pauta.getName())).thenReturn(pauta);
		
		pautaCreateService.execute(pauta);

		verify(pautaRepository, never()).save(any(Pauta.class));
	}
	
	public Pauta getPauta() {
		return Pauta.builder()
				.id(1l)
				.name("Pauta 1")
				.description("Descrição")
				.build();
	}
}
