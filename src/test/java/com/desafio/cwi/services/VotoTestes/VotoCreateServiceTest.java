package com.desafio.cwi.services.VotoTestes;

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

import com.desafio.cwi.client.CpfValidationClient;
import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.exceptions.CpfNotFoundException;
import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.exceptions.SessionExperidException;
import com.desafio.cwi.exceptions.UnableCpfException;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.repositories.PautaRepository;
import com.desafio.cwi.repositories.SessaoRepository;
import com.desafio.cwi.repositories.VotoRepository;
import com.desafio.cwi.responses.CpfValidationResponse;
import com.desafio.cwi.services.voto.VotoCreateService;

@RunWith(MockitoJUnitRunner.class)
public class VotoCreateServiceTest {

	@InjectMocks
	private VotoCreateService votoCreateService;
	
	@Mock
	private PautaRepository pautaRepository;
	
	@Mock
	private SessaoRepository sessaoRepository;
	
	@Mock
	private CpfValidationClient cpfValidationClient;
	
	@Mock
	private VotoRepository votoRepository;

	@Test(expected = PautaNotFoundException.class)
	void deveOcorrerErroAoSalvarQuandoPautaNãoExistir() {

		Voto voto = getVoto();
		votoCreateService.execute(1l, voto);
		
		when(pautaRepository.findById(voto.getPauta().getId())).thenReturn(null);

		verify(votoRepository, never()).save(any(Voto.class));
	}
	
	@Test(expected = PautaNotFoundException.class)
	void deveOcorrerErroAoSalvarQuandoSessaoNãoExistir() {
		
		Voto voto = getVoto();
		
		votoCreateService.execute(1l, voto);
		
		when(sessaoRepository.findById(1l)).thenReturn(null);

		verify(votoRepository, never()).save(any(Voto.class));
	}
	
	@Test(expected = ApiGenericException.class)
	void deveOcorrerErroAoSalvarQuandoRespostaForNula() {
		
		Voto voto = getVoto();
		voto.setResposta(null);
		
		Optional<Pauta> pautaSalva = Optional.ofNullable(getPauta());
		when(pautaRepository.findById(voto.getPauta().getId())).thenReturn(pautaSalva);
		
		Optional<Sessao> sessaoSalva = Optional.ofNullable(getSessao());
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		votoCreateService.execute(1l, voto);

		
		verify(votoRepository, never()).save(any(Voto.class));
	}
	
	@Test(expected = SessionExperidException.class)
	void deveOcorrerErroAoSalvarQuandoSessaoForExpirada() {
		
		Voto voto = getVoto();
		voto.setResposta(true);
		voto.setCpf(null);
		
		Optional<Pauta> pautaSalva = Optional.ofNullable(getPauta());
		when(pautaRepository.findById(voto.getPauta().getId())).thenReturn(pautaSalva);
		
		Optional<Sessao> sessaoSalva = Optional.ofNullable(getSessao());
		sessaoSalva.get().setDataHoraInicio(LocalDateTime.now().plusMinutes(-30));
		sessaoSalva.get().setTempoSessao(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Voto.class));
	}
	
	@Test(expected = CpfNotFoundException.class)
	void deveOcorrerErroAoSalvarQuandoCpfForNulo() {
		
		Voto voto = getVoto();
		voto.setResposta(true);
		voto.setCpf(null);
		
		Optional<Pauta> pautaSalva = Optional.ofNullable(getPauta());
		when(pautaRepository.findById(voto.getPauta().getId())).thenReturn(pautaSalva);
		
		Optional<Sessao> sessaoSalva = Optional.ofNullable(getSessao());
		sessaoSalva.get().setDataHoraInicio(LocalDateTime.now().plusMinutes(30));
		sessaoSalva.get().setTempoSessao(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Voto.class));
	}
	
	@Test(expected = UnableCpfException.class)
	void deveOcorrerErroAoSalvarQuandoApiCpfRetornarUNABLE_TO_VOTE() {
		
		Voto voto = getVoto();
		voto.setResposta(true);
		voto.setCpf("52990556095");
		
		Optional<Pauta> pautaSalva = Optional.ofNullable(getPauta());
		when(pautaRepository.findById(voto.getPauta().getId())).thenReturn(pautaSalva);
		
		Optional<Sessao> sessaoSalva = Optional.ofNullable(getSessao());
		sessaoSalva.get().setDataHoraInicio(LocalDateTime.now().plusMinutes(30));
		sessaoSalva.get().setTempoSessao(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		CpfValidationResponse cpfResponse = new CpfValidationResponse();
		cpfResponse.setStatus("UNABLE_TO_VOTE");
		when(cpfValidationClient.findUserByCpf(voto.getCpf())).thenReturn(cpfResponse);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Voto.class));
	}
	
	@Test(expected = ApiGenericException.class)
	void deveOcorrerErroQuandoCpfExistirNaPauta() {
		
		Voto voto = getVoto();
		voto.setResposta(true);
		voto.setCpf("52990556095");
		
		Optional<Pauta> pautaSalva = Optional.ofNullable(getPauta());
		when(pautaRepository.findById(voto.getPauta().getId())).thenReturn(pautaSalva);
		
		Optional<Sessao> sessaoSalva = Optional.ofNullable(getSessao());
		sessaoSalva.get().setDataHoraInicio(LocalDateTime.now().plusMinutes(30));
		sessaoSalva.get().setTempoSessao(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		CpfValidationResponse cpfResponse = new CpfValidationResponse();
		cpfResponse.setStatus("ABLE_TO_VOTE");
		when(cpfValidationClient.findUserByCpf(voto.getCpf())).thenReturn(cpfResponse);
		
		Optional<Voto> votoSalvo =  Optional.ofNullable(new Voto());;
		when(votoRepository.findByCpfAndPautaId(voto.getCpf(), voto.getPauta().getId())).thenReturn(votoSalvo);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Voto.class));
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
				.id(9l).name("Pauta 1")
				.description("Descrição")
				.build();
	}
	
	public Sessao getSessao() {
		return Sessao.builder()
				.id(new Random()
				.nextLong()).dataHoraInicio(null)
				.pauta(getPauta())
				.build();
	}
}
