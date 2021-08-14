package com.desafio.cwi.services.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.exceptions.PautaNotFoundException;
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.repositories.ScheduleRepository;

@Service
public class ScheduleGetByIdService {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	public Schedule execute(Long id) {
		return scheduleRepository.findById(id).orElseThrow(() -> new PautaNotFoundException(
				"Pauta não encontrada!"));		
	}
}
