package com.desafio.cwi.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoDTO {
	
	@NotNull(message="Uma Pauta deve ser informada!")
	private Long idPauta;
	
	private LocalDateTime dataHoraInicio;
	
	private Long tempoSessao;

}
