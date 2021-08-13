package com.desafio.cwi.dtos;

import java.io.Serializable;

import com.desafio.cwi.models.Pauta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoVotacaoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Pauta pauta;
	private Integer totalSim;
	private Integer totalNao;
	private Integer totalVotos;
}
