package com.desafio.cwi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{
	
	Optional<List<Voto>>findByPautaId(Long id);

	Optional<Voto> findByCpfAndPautaId(String cpf, Long idPauta);

}
