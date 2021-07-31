package com.desafio.cwi.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.services.pauta.PautaCreateService;
import com.desafio.cwi.services.pauta.PautaGetByIdService;

@RunWith(MockitoJUnitRunner.class)
public class PautaControllerTest {

	@InjectMocks
	private PautaCreateService pautaCreateService;
	
	@InjectMocks
	private PautaGetByIdService pautaGetByIdService;

	@Mock
	private PautaRepository pautaRepository;

	@Test
	public void ciraUmaPauta_QuandoMetodoPostForChamado() {

		// dado
		Pauta pauta = Pauta.builder()
				.id(new Random()
				.nextLong()).name("Pauta 1")
				.description("Descrição")
				.build();

		// quando
		Pauta pautaRetorno = pautaCreateService.create(pauta);

		// então
		verify(pautaRepository).save(any(Pauta.class));
	}
}
