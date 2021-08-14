package com.desafio.cwi.services.session;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.exceptions.InvalidDateException;
import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.models.Session;
import com.desafio.cwi.repositories.SessionRepository;
import com.desafio.cwi.services.schedule.ScheduleGetByIdService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessionCreateService {
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	ScheduleGetByIdService scheduleGetByIdService;
	
	public Session execute(Session session) {
		validateStartDateTime(session);
		verifySessionTime(session);
		validateScheduleExists(session);
		session.setSchedule(scheduleGetByIdService.execute(session.getSchedule().getId()));
		var sessionSaved = sessionRepository.save(session);
		log.info("Sessão criada com sucesso!");
		return sessionSaved;
	}
	
	public Session verifySessionTime(Session session) {
		if (session.getStartDateTime() != null && session.getSessionTime() <= 0) {
			throw new ApiGenericException("Tempo não pode ser zero ou negativo");
		}
		if (session.getSessionTime() == null) {
			session.setSessionTime(1l);
		}
		return session;
	}
	
	public void validateScheduleExists(Session session) {
		if (session.getSchedule() == null || session.getSchedule().getId() == null) {
			throw new PautaNotFoundException("Uma Pauta deve ser informada!");
		}
	}
	
	public Session validateStartDateTime(Session session) {
		if (session.getStartDateTime() == null ) {
			session.setStartDateTime(LocalDateTime.now());
		} else if (session.getStartDateTime().isBefore(LocalDateTime.now())) {
			throw new InvalidDateException("A data não pode ser menor que a data atual!");
		}
		return session;
	}
}
