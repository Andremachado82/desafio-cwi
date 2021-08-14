package com.desafio.cwi.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDTO {
	
	@NotNull(message="Uma Pauta deve ser informada!")
	private Long idSchedule;
	
	@NotNull(message="Uma Sessão deve ser informada!")
	private Long idSession;
	
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@NotNull(message = "Resposta de voto obrigatória")
	private String answer;
}
