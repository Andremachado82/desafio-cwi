package com.desafio.cwi.dtos;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoDTO {
	
	@NotNull
	private Long idPauta;
	
	@NotNull
	private Long idSessao;
	
	@NotBlank
	private String cpf;
	
	@NotNull
	private Boolean resposta;
}
