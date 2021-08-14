package com.desafio.cwi.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FormatCPF {
	 public static String formatCpf(String cpf) {
		return cpf.trim().replaceAll("[^0-9]+", "");
	}
}
