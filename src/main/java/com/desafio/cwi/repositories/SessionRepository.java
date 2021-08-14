package com.desafio.cwi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{
	Optional<Session> findByScheduleId(Long id);
}
