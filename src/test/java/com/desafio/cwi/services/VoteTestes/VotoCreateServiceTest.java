package com.desafio.cwi.services.VoteTestes;

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
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.models.Session;
import com.desafio.cwi.models.Vote;
import com.desafio.cwi.repositories.ScheduleRepository;
import com.desafio.cwi.repositories.SessionRepository;
import com.desafio.cwi.repositories.VoteRepository;
import com.desafio.cwi.responses.CpfValidationResponse;
import com.desafio.cwi.services.vote.VoteCreateService;

@RunWith(MockitoJUnitRunner.class)
public class VotoCreateServiceTest {
	final String CPF = "52990556095";
	final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
	final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
	final String NAME_SCHEDULE = "Pauta 1";
	final String DESCRIPTION_SCHEDULE = "Descrição";

	@InjectMocks
	private VoteCreateService votoCreateService;
	
	@Mock
	private ScheduleRepository pautaRepository;
	
	@Mock
	private SessionRepository sessaoRepository;
	
	@Mock
	private CpfValidationClient cpfValidationClient;
	
	@Mock
	private VoteRepository votoRepository;

	@Test(expected = PautaNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoPautaNãoExistir() {

		Vote voto = getVote();
		votoCreateService.execute(1l, voto);
		
		when(pautaRepository.findById(voto.getSchedule().getId())).thenReturn(null);

		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	@Test(expected = PautaNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoSessaoNãoExistir() {
		
		Vote voto = getVote();
		
		votoCreateService.execute(1l, voto);
		
		when(sessaoRepository.findById(1l)).thenReturn(null);

		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroAoSalvarQuandoRespostaForNula() {
		
		Vote voto = getVote();
		voto.setAnswer(null);
		
		Optional<Schedule> pautaSalva = Optional.ofNullable(getSchedule());
		when(pautaRepository.findById(voto.getSchedule().getId())).thenReturn(pautaSalva);
		
		Optional<Session> sessaoSalva = Optional.ofNullable(getSession());
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		votoCreateService.execute(1l, voto);

		
		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	@Test(expected = SessionExperidException.class)
	public void deveOcorrerErroAoSalvarQuandoSessaoForExpirada() {
		
		Vote voto = getVote();
		voto.setAnswer(true);
		voto.setCpf(null);
		
		Optional<Schedule> pautaSalva = Optional.ofNullable(getSchedule());
		when(pautaRepository.findById(voto.getSchedule().getId())).thenReturn(pautaSalva);
		
		Optional<Session> sessaoSalva = Optional.ofNullable(getSession());
		sessaoSalva.get().setStartDateTime(null);
		sessaoSalva.get().setSessionTime(null);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	@Test(expected = CpfNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoCpfForNulo() {
		
		Vote voto = getVote();
		voto.setAnswer(true);
		voto.setCpf(null);
		
		Optional<Schedule> pautaSalva = Optional.ofNullable(getSchedule());
		when(pautaRepository.findById(voto.getSchedule().getId())).thenReturn(pautaSalva);
		
		Optional<Session> sessaoSalva = Optional.ofNullable(getSession());
		sessaoSalva.get().setStartDateTime(LocalDateTime.now().plusMinutes(30));
		sessaoSalva.get().setSessionTime(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	@Test(expected = UnableCpfException.class)
	public void deveOcorrerErroAoSalvarQuandoApiCpfRetornarUNABLE_TO_VOTE() {
		
		Vote voto = getVote();
		voto.setAnswer(true);
		voto.setCpf(CPF);
		
		Optional<Schedule> pautaSalva = Optional.ofNullable(getSchedule());
		when(pautaRepository.findById(voto.getSchedule().getId())).thenReturn(pautaSalva);
		
		Optional<Session> sessaoSalva = Optional.ofNullable(getSession());
		sessaoSalva.get().setStartDateTime(LocalDateTime.now().plusMinutes(30));
		sessaoSalva.get().setSessionTime(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		CpfValidationResponse cpfResponse = new CpfValidationResponse();
		cpfResponse.setStatus(UNABLE_TO_VOTE);
		when(cpfValidationClient.findUserByCpf(voto.getCpf())).thenReturn(cpfResponse);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroQuandoCpfExistirNaPauta() {
		
		Vote voto = getVote();
		voto.setAnswer(true);
		voto.setCpf(CPF);
		
		Optional<Schedule> pautaSalva = Optional.ofNullable(getSchedule());
		when(pautaRepository.findById(voto.getSchedule().getId())).thenReturn(pautaSalva);
		
		Optional<Session> sessaoSalva = Optional.ofNullable(getSession());
		sessaoSalva.get().setStartDateTime(LocalDateTime.now().plusMinutes(30));
		sessaoSalva.get().setSessionTime(1l);
		when(sessaoRepository.findById(1l)).thenReturn(sessaoSalva);
		
		CpfValidationResponse cpfResponse = new CpfValidationResponse();
		cpfResponse.setStatus(ABLE_TO_VOTE);
		when(cpfValidationClient.findUserByCpf(voto.getCpf())).thenReturn(cpfResponse);
		
		Optional<Vote> votoSalvo =  Optional.ofNullable(new Vote());;
		when(votoRepository.findByCpfAndScheduleId(voto.getCpf(), voto.getSchedule().getId())).thenReturn(votoSalvo);
		
		votoCreateService.execute(1l, voto);

		verify(votoRepository, never()).save(any(Vote.class));
	}
	
	public Vote getVote() {
		return Vote.builder()
				.id(new Random()
				.nextLong())
				.schedule(getSchedule())
				.build();
	}
	
	public Schedule getSchedule() {
		return Schedule.builder()
				.id(9l).name(NAME_SCHEDULE)
				.description(DESCRIPTION_SCHEDULE)
				.build();
	}
	
	public Session getSession() {
		return Session.builder()
				.id(new Random()
				.nextLong()).startDateTime(null)
				.schedule(getSchedule())
				.build();
	}
}
