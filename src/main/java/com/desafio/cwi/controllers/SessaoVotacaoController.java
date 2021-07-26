package com.desafio.cwi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.models.SessaoVotacao;
import com.desafio.cwi.services.sessao.SessaoVotacaoCreateService;
import com.desafio.cwi.services.sessao.SessaoVotacaoDeleteService;
import com.desafio.cwi.services.sessao.SessaoVotacaoFindAllService;
import com.desafio.cwi.services.sessao.SessaoVotacaoGetByIdService;

@RestController
public class SessaoVotacaoController {
	
	@Autowired
	SessaoVotacaoCreateService sessaoVotacaoCreateService;
	
	@Autowired
	SessaoVotacaoFindAllService sessaoVotacaoFindAllService;
	
	@Autowired
	SessaoVotacaoGetByIdService sessaoVotacaoGetByIdService;
	
	@Autowired
	SessaoVotacaoDeleteService sessaoVotacaoDeleteService;
	
	@PostMapping("/v1/pauta/{idPauta}/sessao")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SessaoVotacao create(@PathVariable Long idPauta, @RequestBody @Valid SessaoVotacao sessao) {
		return sessaoVotacaoCreateService.create(sessao);
	}
	
	@GetMapping("/v1/pauta/sessao")
	@ResponseStatus(code = HttpStatus.OK)
	public List<SessaoVotacao> findAll() {
		return sessaoVotacaoFindAllService.findAll();
	} 
	
	@GetMapping("/v1/pauta/sessao/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public SessaoVotacao findById(@PathVariable Long id) throws Exception {
		return sessaoVotacaoGetByIdService.findById(id);
	}
	
	@DeleteMapping("/v1/pauta/sessao/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable Long id) throws Exception {
		sessaoVotacaoDeleteService.deleteById(id);
	}
}
