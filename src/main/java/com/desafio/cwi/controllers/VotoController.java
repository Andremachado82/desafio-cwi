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
@RequestMapping("/v1/voto")
public class VotoController {
	
	@Autowired
	VotoCreateService votoCreateService;
	
	@Autowired
	VotoFindAllService votoFindAllService;
	
	@Autowired
	VotoFindByIdService votoFindByIdService;
	
	@Autowired
	votoDeleteByIdService votoDeleteByIdService;

	@PostMapping
	public Voto create(@RequestBody Voto voto) {
		return votoCreateService.create(voto);
	} 
	
	@GetMapping("/list")
	public ResponseEntity<List<Voto>> findAll() {
		return ResponseEntity.ok(votoFindAllService.findAll());
	} 
	
	@GetMapping("/{id}")
	public Voto findById(@PathVariable Long id) throws Exception {
		return votoFindByIdService.findById(id);
	} 
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) throws Exception {
		votoDeleteByIdService.deleteById(id);
	} 
}
