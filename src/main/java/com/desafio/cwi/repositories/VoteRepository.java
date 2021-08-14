package com.desafio.cwi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{
	Optional<Vote> findByCpfAndScheduleId(String cpf, Long idSchedule);
	
	Optional<List<Vote>> findByScheduleId(Long id);
}
