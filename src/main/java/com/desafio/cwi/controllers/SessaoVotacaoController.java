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

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.services.sessao.SessaoVotacaoCreateService;
import com.desafio.cwi.services.sessao.SessaoVotacaoFindAllService;
import com.desafio.cwi.services.sessao.SessaoVotacaoGetByIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Sessão controller")
@RequestMapping("/v1/pauta/")
public class SessaoVotacaoController {
	
	@Autowired
	SessaoVotacaoCreateService sessaoVotacaoCreateService;
	
	@Autowired
	SessaoVotacaoFindAllService sessaoVotacaoFindAllService;
	
	@Autowired
	SessaoVotacaoGetByIdService sessaoVotacaoGetByIdService;
	
	@PostMapping("{idPauta}/sessao")
	@ApiOperation(value="Cria uma sessão em uma Pauta")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SessaoVotacao create(@PathVariable Long idPauta, @RequestBody @Valid SessaoVotacao sessao) {
		return sessaoVotacaoCreateService.create(idPauta, sessao);
	}
	
	@GetMapping("sessao")
	@ApiOperation(value="Retorna uma lista de Sessões")
	@ResponseStatus(code = HttpStatus.OK)
	public List<SessaoVotacao> findAll() {
		return sessaoVotacaoFindAllService.findAll();
	} 
	
	@GetMapping("sessao/{id}")
	@ApiOperation(value="Retorna uma sessão por ID")
	@ResponseStatus(code = HttpStatus.OK)
	public SessaoVotacao findById(@PathVariable Long id) throws Exception {
		return sessaoVotacaoGetByIdService.findById(id);
	}
}
