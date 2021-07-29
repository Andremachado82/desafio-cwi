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

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.services.pauta.PautaCreateService;
import com.desafio.cwi.services.pauta.PautaDeleteService;
import com.desafio.cwi.services.pauta.PautaGetAllService;
import com.desafio.cwi.services.pauta.PautaGetByIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Pauta controller")
public class PautaController {
	
	@Autowired
	private PautaCreateService pautaCreateService;
	
	@Autowired
	private PautaGetAllService pautaGetAllService;
	
	@Autowired
	PautaGetByIdService pautaGetByIdService;
	
	@Autowired
	PautaDeleteService pautaDeleteService;
	
	@PostMapping("/v1/pautas")
	@ApiOperation(value="Cria uma pauta")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pauta create(@RequestBody @Valid Pauta pauta) {
		
		return pautaCreateService.create(pauta);
	}
	
	@GetMapping("/v1/pautas")
	@ApiOperation(value="Retorna uma lista de pautas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Pauta> getAllPauta() {
		
		return pautaGetAllService.getAllPauta();
	}
	
	@GetMapping("/v1/pautas/{id}")
	@ApiOperation(value="Retorna uma pauta por ID")
	@ResponseStatus(code = HttpStatus.OK)	
	public Pauta getByIdPauta(@PathVariable Long id) throws Exception {
		
		return pautaGetByIdService.getById(id);
	}
	
	@DeleteMapping("/v1/pautas/{id}")
	@ApiOperation(value="Deleta uma pauta")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable Long id) throws Exception {
		pautaDeleteService.deleteById(id);
	}

}
