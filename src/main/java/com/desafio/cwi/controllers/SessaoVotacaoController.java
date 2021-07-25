package com.desafio.cwi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.services.sessao.SessaoVotacaoCreateService;

@RestController
public class SessaoVotacaoController {
	
	@Autowired
	SessaoVotacaoCreateService sessaoVotacaoCreateService;
	
	@PostMapping("/v1/pauta/sessao")
	public SessaoVotacao create(@RequestBody SessaoVotacao sessao) {
		return sessaoVotacaoCreateService.create(sessao);
	} 
}
