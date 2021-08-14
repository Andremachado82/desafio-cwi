package com.desafio.cwi.v2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.ResultVoteDto;
import com.desafio.cwi.services.resultVote.ResultVoteByScheduleIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST ResultVote controller")
@RequestMapping("/v2/resultVote")
public class ResultVoteController {
	
	@Autowired
	ResultVoteByScheduleIdService resultVoteByScheduleIdService;
	
	@ApiOperation(value = "List Result of Vote")
	@GetMapping("/{idSchedule}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResultVoteDto execute(@PathVariable Long idSchedule) {
		return resultVoteByScheduleIdService.getResultVote(idSchedule);
	}
}
