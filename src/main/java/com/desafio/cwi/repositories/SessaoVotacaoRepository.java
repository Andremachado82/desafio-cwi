package com.desafio.cwi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>{
	
	Optional<List<SessaoVotacao>> findByPautaId(Long id);

}
