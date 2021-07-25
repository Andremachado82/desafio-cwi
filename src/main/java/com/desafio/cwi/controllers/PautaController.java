package com.desafio.cwi.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cwi.models.Pauta;
import com.desafio.cwi.services.PautaCreateService;
import com.desafio.cwi.services.PautaDeleteService;
import com.desafio.cwi.services.PautaGetAllService;
import com.desafio.cwi.services.PautaGetByIdService;
import com.desafio.cwi.services.PautaUpdateService;

@RestController
@RequestMapping("/v1/pauta")
public class PautaController {
	
	@Autowired
	private PautaCreateService pautaCreateService;
	
	@Autowired
	private PautaGetAllService pautaGetAllService;
	
	@Autowired
	PautaGetByIdService pautaGetByIdService;
	
	@Autowired
	private PautaUpdateService pautaUpdateService;
	
	@Autowired
	PautaDeleteService pautaDeleteService;
	
	@PostMapping
	public ResponseEntity<Pauta> create(@RequestBody Pauta pauta) {
		
		return ResponseEntity.ok().body(pautaCreateService.create(pauta));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Pauta>> getAllPauta() {
		
		return ResponseEntity.ok(pautaGetAllService.getAllPauta());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pauta> getByIdPauta(@PathVariable Long id) throws Exception {
		
		return ResponseEntity.ok(pautaGetByIdService.getById(id));
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<Pauta> update(@Valid @PathVariable("id") Long id, @RequestBody Pauta pauta) throws Exception {
		
		return ResponseEntity.ok().body(pautaUpdateService.update(id, pauta));
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Long id) throws Exception {
		pautaDeleteService.deleteById(id);
	}

}
