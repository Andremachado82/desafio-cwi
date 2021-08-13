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
public class SessaoDTO {
	
	private Long idPauta;
	
	private LocalDateTime dataHoraInicio;
	
	private Long tempoSessao;
}
