package com.desafio.cwi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.ResultadoVotacaoDto;
import com.desafio.cwi.services.votacao.ResultadoVotacaoByPautaIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST Votação controller")
@RequestMapping("/v1/resultadoVotacao")
public class ResultadoVotacaoController {
	
	@Autowired
	ResultadoVotacaoByPautaIdService resultadoVotacaoService;
	
	@ApiOperation(value = "Lista o Resultado da Votação")
	@GetMapping("/{idPauta}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResultadoVotacaoDto execute(@PathVariable Long idPauta) {
		return resultadoVotacaoService.getResultadoVotacao(idPauta);
	}
}
