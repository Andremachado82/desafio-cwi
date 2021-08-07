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

import com.desafio.cwi.dtos.VotoDTO;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.services.voto.VotoCreateService;
import com.desafio.cwi.services.voto.VotoFindByIdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Voto controller")
@RequestMapping("/v1/votos")
public class VotoController {
	
	@Autowired
	VotoCreateService votoCreateService;
	
	@Autowired
	VotoFindByIdService votoFindByIdService;

	@PostMapping()
	@ApiOperation(value="Cria um voto em uma sessão dentro de uma pauta")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Voto create(@RequestBody @Valid VotoDTO votoDTO) {
		Voto voto = Voto.builder()
					.cpf(votoDTO.getCpf())
					.resposta(votoDTO.getResposta())
					.pauta(new Pauta(votoDTO.getIdPauta(), null, null))
					.build();
		return votoCreateService.execute(votoDTO.getIdSessao(), voto);
	} 
	
	@GetMapping("/sessoes/votos/{id}")
	@ApiOperation(value="Retorna um voto por ID")
	@ResponseStatus(code = HttpStatus.OK)
	public Voto findById(@PathVariable Long id) throws Exception {
		return votoFindByIdService.findById(id);
	} 
}
