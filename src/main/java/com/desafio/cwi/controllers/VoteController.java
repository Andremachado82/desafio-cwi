package com.desafio.cwi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.VoteDTO;
import com.desafio.cwi.models.Schedule;
import com.desafio.cwi.models.Vote;
import com.desafio.cwi.services.vote.VoteCreateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Vote controller")
@RequestMapping("/v1/vote")
public class VoteController {
	
	@Autowired
	VoteCreateService voteCreateService;

	@PostMapping()
	@ApiOperation(value="Create a vote on an session within an schedule")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Vote create(@RequestBody @Valid VoteDTO voteDTO) {
		var vote = Vote.builder()
					.cpf(voteDTO.getCpf())
					.answer(voteCreateService.verifyAnswer(voteDTO.getAnswer()))
					.schedule(new Schedule(voteDTO.getIdSchedule(), null, null))
					.build();
		return voteCreateService.execute(voteDTO.getIdSession(), vote);
	} 
}
