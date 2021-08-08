package com.desafio.cwi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.VotacaoDto;
import com.desafio.cwi.services.votacao.votacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API REST Votação controller")
@RequestMapping("/v1/votacao")
public class VotacaoController {
	
	@Autowired
	votacaoService votacaoService;
	
	@ApiOperation(value = "Lista o Resultado da Votação")
	@GetMapping("/resultado/{idPauta}")
	@ResponseStatus(code = HttpStatus.OK)
	public VotacaoDto findVotosByPautaId(@PathVariable Long idPauta) {
		return votacaoService.getResultVotacao(idPauta);
	}

}
