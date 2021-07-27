package com.desafio.cwi.exceptions;

import lombok.Data;

@Data
public class StandardError {

	private Integer status;

	private String msg;

	private String dataExcecao;

	public StandardError(Integer status, String msg, String dataExcecao) {
		super();
		this.status = status;
		this.msg = msg;
		this.dataExcecao = dataExcecao;
	}

}
