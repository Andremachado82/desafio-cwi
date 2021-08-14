package com.desafio.cwi.dtos;

import java.io.Serializable;

import com.desafio.cwi.models.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVoteDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Schedule schedule;
	private Integer totalSim;
	private Integer totalNao;
	private Integer totalVotes;
}
