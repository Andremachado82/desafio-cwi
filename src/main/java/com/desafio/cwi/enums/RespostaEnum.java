package com.desafio.cwi.enums;

import lombok.Getter;

@Getter
public enum RespostaEnum {
	
	SIM("Sim"),
	NAO("Não");
	
	private String resposta;

	RespostaEnum(String value) {
        this.resposta = value;
    }
}
