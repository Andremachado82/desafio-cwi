package com.desafio.cwi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.SessionDTO;
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.models.Session;
import com.desafio.cwi.services.session.SessionCreateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Session controller")
@RequestMapping("/v1/session")
public class SessionController {
	
	@Autowired
	SessionCreateService sessionCreateService;
	
	@PostMapping
	@ApiOperation(value="Create a session on  an schedule")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Session create(@RequestBody SessionDTO sessionDTO) {
		var session = Session.builder()
						.startDateTime(sessionDTO.getStartDateTime())
						.sessionTime(sessionDTO.getSessionTime())
						.schedule(new Schedule(sessionDTO.getIdSchedule(), null, null))
						.build();
		return sessionCreateService.execute(session);
	}
}
