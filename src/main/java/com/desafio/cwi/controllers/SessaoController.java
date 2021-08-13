package com.desafio.cwi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.SessaoDTO;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Sessao;
import com.desafio.cwi.services.sessao.SessaoCreateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Sessão controller")
@RequestMapping("/v1/sessao")
public class SessaoController {
	
	@Autowired
	SessaoCreateService sessaoCreateService;
	
	@PostMapping
	@ApiOperation(value="Cria uma sessão em uma Pauta")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Sessao create(@RequestBody SessaoDTO sessaoDTO) {
		var sessao = Sessao.builder()
						.dataHoraInicio(sessaoDTO.getDataHoraInicio())
						.tempoSessao(sessaoDTO.getTempoSessao())
						.pauta(new Pauta(sessaoDTO.getIdPauta(), null, null))
						.build();
		return sessaoCreateService.execute(sessao);
	}
}
