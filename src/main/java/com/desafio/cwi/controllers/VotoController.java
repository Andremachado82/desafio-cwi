package com.desafio.cwi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.models.Voto;
import com.desafio.cwi.services.voto.VotoCreateService;

@RestController
@RequestMapping("/v1/voto")
public class VotoController {
	
	@Autowired
	VotoCreateService votoCreateService;

	@PostMapping
	public Voto create(@RequestBody Voto voto) {
		return votoCreateService.create(voto);
	} 
}
