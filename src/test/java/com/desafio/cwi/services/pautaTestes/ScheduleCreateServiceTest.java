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
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.repositories.ScheduleRepository;
import com.desafio.cwi.services.schedule.ScheduleCreateService;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleCreateServiceTest {
	final String NAME_SCHEDULE = "Pauta 1";
	final String DESCRIPTION_SCHEDULE = "Descrição";

	@InjectMocks
	private ScheduleCreateService scheduleCreateService;

	@Mock
	private ScheduleRepository scheduleRepository;

	@Test
	public void devecriarUmaPautaComSucesso() {

		Schedule schedule = getSchedule();

		scheduleCreateService.execute(schedule);

		verify(scheduleRepository).save(any(Schedule.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveRetornarUmErroQuandoSalvarPautaSemNome() {

		Schedule schedule = getSchedule();
		schedule.setName("");
		scheduleCreateService.execute(schedule);

		verify(scheduleRepository, never()).save(any(Schedule.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveRetornarUmErroQuandoSalvarPautaComNomeNulo() {

		Schedule schedule = getSchedule();
		schedule.setName(null);
		scheduleCreateService.execute(schedule);

		verify(scheduleRepository, never()).save(any(Schedule.class));
	}
	
	@Test(expected = ApiGenericException.class)
	public void deveRetornarUmErroQuandoSalvarPautaComNomeExistente() {
		Schedule schedule = getSchedule();			
		
		when(scheduleRepository.findByName(schedule.getName())).thenReturn(schedule);
		
		scheduleCreateService.execute(schedule);

		verify(scheduleRepository, never()).save(any(Schedule.class));
	}
	
	public Schedule getSchedule() {
		return Schedule.builder()
				.id(1l)
				.name(NAME_SCHEDULE)
				.description(DESCRIPTION_SCHEDULE)
				.build();
	}
}
