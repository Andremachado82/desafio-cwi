package com.desafio.cwi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.ScheduleDTO;
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.services.schedule.ScheduleCreateService;
import com.desafio.cwi.services.schedule.ScheduleGetAllService;
import com.desafio.cwi.services.schedule.ScheduleGetByIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Schedule controller")
@RequestMapping("/v1/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleCreateService scheduleCreateService;
	
	@Autowired
	ScheduleGetAllService scheduleGetAllService;
	
	@Autowired
	ScheduleGetByIdService scheduleGetByIdService;
	
	@PostMapping
	@ApiOperation(value="Create schedule")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Schedule create(@RequestBody @Valid ScheduleDTO scheduleDTO) {
		var schedule = Schedule.builder()
				.name(scheduleDTO.getName())
				.description(scheduleDTO.getDescription())
				.build();
		return scheduleCreateService.execute(schedule);
	}
	
	@GetMapping
	@ApiOperation(value="Return list of schedules")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Schedule> findAll() {
		return scheduleGetAllService.execute();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Return  schedules by ID")
	@ResponseStatus(code = HttpStatus.OK)	
	public Schedule findById(@PathVariable Long id) {
		return scheduleGetByIdService.execute(id);
	}
}
