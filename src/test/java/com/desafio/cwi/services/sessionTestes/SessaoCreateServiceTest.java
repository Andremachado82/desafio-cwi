package com.desafio.cwi.services.sessionTestes;

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
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.models.Session;
import com.desafio.cwi.repositories.ScheduleRepository;
import com.desafio.cwi.repositories.SessionRepository;
import com.desafio.cwi.services.schedule.ScheduleGetByIdService;
import com.desafio.cwi.services.session.SessionCreateService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SessaoCreateServiceTest {
	final String NAME_SCHEDULE = "Pauta 1";
	final String DESCRIPTION_SCHEDULE = "Descrição";

	@InjectMocks
	private SessionCreateService sessionCreateService;
	
	@Mock
	private ScheduleGetByIdService scheduleGetByIdService;
	
	@Mock
	private ScheduleRepository scheduleRepository;
	
	@Mock
	private SessionRepository sessionRepository;

	@Test(expected = InvalidDateException.class)
	public void deveOcorrerErroQuandoSalvarSessaoComDataAntesDaDataAtual() {

		Session session = getSession();
		session.setStartDateTime(LocalDateTime.of(2018, 07, 22, 10, 15, 30));
		
		sessionCreateService.execute(session);

		verify(sessionRepository, never()).save(any(Session.class));
	}
	
	@Test(expected = PautaNotFoundException.class)
	public void deveOcorrerErroAoSalvarQuandoNaoExistirPauta() {
		
		Session session = getSession();
		session.setSchedule(null);
		
		sessionCreateService.execute(session);

		verify(sessionRepository, never()).save(any(Session.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveOcorrerErroAoSalvarQuandoTempoSessaoForMenorQueUm() {
		
		Session session = getSession();
		session.setSessionTime(-1l);
		
		sessionCreateService.execute(session);
		
		verify(sessionRepository, never()).save(any(Session.class));
	}
	
	@Test
	public void deveSalvarSessaoComTempoSessaoPadraoQuandoTempoForNulo() {
		
		Session session = getSession();
		session.setSessionTime(null);
		
		sessionCreateService.execute(session);
		session.setSchedule(getSchedule());
		
		when(scheduleGetByIdService.execute(session.getSchedule().getId())).thenReturn(getSchedule());
		
		Optional<Schedule> pautaSalva = Optional.ofNullable(new Schedule());
		when(scheduleRepository.findById(session.getSchedule().getId())).thenReturn(pautaSalva);
		
		verify(sessionRepository).save(any(Session.class));
	}
	
	public Session getSession() {
		return Session.builder()
				.id(new Random()
				.nextLong()).startDateTime(null)
				.schedule(getSchedule())
				.build();
	}
	
	public Schedule getSchedule() {
		return Schedule.builder()
				.id(9l).name(NAME_SCHEDULE)
				.description(DESCRIPTION_SCHEDULE)
				.build();
	}
}
