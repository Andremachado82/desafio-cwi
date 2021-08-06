package com.desafio.cwi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, Long>{
	
	Optional<Sessao> findByPautaId(Long id);
	
}
