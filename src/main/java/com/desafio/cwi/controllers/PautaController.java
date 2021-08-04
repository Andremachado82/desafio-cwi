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

import com.desafio.cwi.dtos.PautaDTO;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.services.pauta.PautaCreateService;
import com.desafio.cwi.services.pauta.PautaGetAllService;
import com.desafio.cwi.services.pauta.PautaGetByIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Pauta controller")
@RequestMapping("/v1/pautas/")
public class PautaController {
	
	@Autowired
	PautaCreateService pautaCreateService;
	
	@Autowired
	PautaGetAllService pautaGetAllService;
	
	@Autowired
	PautaGetByIdService pautaGetByIdService;
	
	@PostMapping
	@ApiOperation(value="Cria uma pauta")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pauta create(@RequestBody @Valid PautaDTO pautaDTO) {
		Pauta pauta = Pauta.builder()
				.name(pautaDTO.getName())
				.description(pautaDTO.getDescription())
				.build();
		return pautaCreateService.create(pauta);
	}
	
	@GetMapping
	@ApiOperation(value="Retorna uma lista de pautas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Pauta> findAll() {
		return pautaGetAllService.findAll();
	}
	
	@GetMapping("{id}")
	@ApiOperation(value="Retorna uma pauta por ID")
	@ResponseStatus(code = HttpStatus.OK)	
	public Pauta findById(@PathVariable Long id) throws Exception {
		return pautaGetByIdService.findById(id);
	}
}
