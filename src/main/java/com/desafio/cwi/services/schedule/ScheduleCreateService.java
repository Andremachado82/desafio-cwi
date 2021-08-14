package com.desafio.cwi.services.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.ApiGenericException;
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.repositories.ScheduleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class ScheduleCreateService {
	
	@Autowired
	ScheduleRepository scheduleRepository;

	public Schedule execute(Schedule schedule) {
		if (schedule.getName() == null || schedule.getName().isBlank()) {
			throw new ApiGenericException("Nome da pauta não deve estar em branco ou nula!");
		}
		if (schedule.getName().length() < 2) {
			throw new ApiGenericException("Nome da pauta deve conter mais de duas letras!");
		}
		var existsSchedule = scheduleRepository.findByName(schedule.getName());
		
		if (existsSchedule != null) {
			throw new ApiGenericException("Nome da pauta existente!");
		}
		Schedule scheduleSaved = scheduleRepository.save(schedule);
		log.info("Pauta criada com sucesso!");
		return scheduleSaved;
	}
}