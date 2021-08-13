package com.desafio.cwi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.dtos.VotoDTO;
import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.models.Voto;
import com.desafio.cwi.services.voto.VotoCreateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="API REST Voto controller")
@RequestMapping("/v1/votos")
public class VotoController {
	
	@Autowired
	VotoCreateService votoCreateService;

	@PostMapping()
	@ApiOperation(value="Cria um voto em uma sessão dentro de uma pauta")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Voto create(@RequestBody @Valid VotoDTO votoDTO) {
		var voto = Voto.builder()
					.cpf(votoDTO.getCpf())
					.resposta(votoDTO.getResposta())
					.pauta(new Pauta(votoDTO.getIdPauta(), null, null))
					.build();
		return votoCreateService.execute(votoDTO.getIdSessao(), voto);
	} 
}
