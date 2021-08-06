package com.desafio.cwi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{

	Pauta findByName(String nome);	
}
