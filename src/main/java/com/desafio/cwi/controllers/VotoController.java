package com.desafio.cwi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.models.Voto;
import com.desafio.cwi.services.voto.VotoCreateService;
import com.desafio.cwi.services.voto.VotoFindAllService;
import com.desafio.cwi.services.voto.VotoFindByIdService;
import com.desafio.cwi.services.voto.votoDeleteByIdService;

@RestController
public class VotoController {
	
	@Autowired
	VotoCreateService votoCreateService;
	
	@Autowired
	VotoFindAllService votoFindAllService;
	
	@Autowired
	VotoFindByIdService votoFindByIdService;
	
	@Autowired
	votoDeleteByIdService votoDeleteByIdService;

	@PostMapping("/v1/pautas/{id}/sessoes/{id}/votos")
	public Voto create(@RequestBody Voto voto) {
		return votoCreateService.create(voto);
	} 
	
	@GetMapping("/v1/pautas/sessoes/votos")
	public ResponseEntity<List<Voto>> findAll() {
		return ResponseEntity.ok(votoFindAllService.findAll());
	} 
	
	@GetMapping("v1/pautas/sessoes/votos/{id}")
	public Voto findById(@PathVariable Long id) throws Exception {
		return votoFindByIdService.findById(id);
	} 
	
	@DeleteMapping("v1/pautas/sessoes/votos/{id}")
	public void deleteById(@PathVariable Long id) throws Exception {
		votoDeleteByIdService.deleteById(id);
	} 
}
