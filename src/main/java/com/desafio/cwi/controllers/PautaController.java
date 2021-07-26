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
import com.desafio.cwi.services.pauta.PautaCreateService;
import com.desafio.cwi.services.pauta.PautaDeleteService;
import com.desafio.cwi.services.pauta.PautaGetAllService;
import com.desafio.cwi.services.pauta.PautaGetByIdService;
import com.desafio.cwi.services.pauta.PautaUpdateService;

@RestController
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
	
	@PostMapping("/v1/pautas")
	public ResponseEntity<Pauta> create(@RequestBody Pauta pauta) {
		
		return ResponseEntity.ok().body(pautaCreateService.create(pauta));
	}
	
	@GetMapping("/v1/pautas")
	public ResponseEntity<List<Pauta>> getAllPauta() {
		
		return ResponseEntity.ok(pautaGetAllService.getAllPauta());
	}
	
	@GetMapping("/v1/pautas/{id}")
	public ResponseEntity<Pauta> getByIdPauta(@PathVariable Long id) throws Exception {
		
		return ResponseEntity.ok(pautaGetByIdService.getById(id));
	}
	

	@PutMapping("/v1/pautas/{id}")
	public ResponseEntity<Pauta> update(@Valid @PathVariable("id") Long id, @RequestBody Pauta pauta) throws Exception {
		
		return ResponseEntity.ok().body(pautaUpdateService.update(id, pauta));
	}
	
	@DeleteMapping("/v1/pautas/{id}")
	public void deleteById(@PathVariable Long id) throws Exception {
		pautaDeleteService.deleteById(id);
	}

}
