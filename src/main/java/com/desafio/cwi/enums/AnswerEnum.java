package com.desafio.cwi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerEnum {
	
	SIM("Sim"),
	NAO("Não");
	
	private final String answer;
}
