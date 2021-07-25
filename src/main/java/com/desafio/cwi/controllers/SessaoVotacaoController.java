package com.desafio.cwi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.services.sessao.SessaoVotacaoCreateService;
import com.desafio.cwi.services.sessao.SessaoVotacaoFindAllService;

@RestController
public class SessaoVotacaoController {
	
	@Autowired
	SessaoVotacaoCreateService sessaoVotacaoCreateService;
	
	@Autowired
	SessaoVotacaoFindAllService sessaoVotacaoFindAllService;
	
	@PostMapping("/v1/pauta/{id}/sessao")
	public SessaoVotacao create(@PathVariable Long id, @RequestBody SessaoVotacao sessao) {
		return sessaoVotacaoCreateService.create(sessao);
	}
	
	@GetMapping("/v1/pauta/sessao")
	public List<SessaoVotacao> findAll() {
		return sessaoVotacaoFindAllService.findAll();
	} 
}
