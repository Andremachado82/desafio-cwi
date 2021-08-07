package com.desafio.cwi.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FormataCPF {

	 public static String formataCpf(String cpf) {
		return cpf.trim().replaceAll("[^0-9]+", "");
	}

}
