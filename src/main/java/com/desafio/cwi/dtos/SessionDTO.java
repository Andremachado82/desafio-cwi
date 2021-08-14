package com.desafio.cwi.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionDTO {
	
	private Long idSchedule;
	
	private LocalDateTime startDateTime;
	
	private Long sessionTime;
}
