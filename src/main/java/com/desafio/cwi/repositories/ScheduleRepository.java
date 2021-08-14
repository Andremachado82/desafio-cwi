package com.desafio.cwi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cwi.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	Schedule findByName(String name);	
}
