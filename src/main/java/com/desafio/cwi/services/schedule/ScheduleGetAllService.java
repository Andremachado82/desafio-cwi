package com.desafio.cwi.services.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.repositories.ScheduleRepository;

@Service
public class ScheduleGetAllService {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	public List<Schedule> execute() {
		return scheduleRepository.findAll();
	}
}
