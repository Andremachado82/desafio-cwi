package com.desafio.cwi.exceptions;

public class SessionNotFoundException  extends RuntimeException {
	public SessionNotFoundException(String msg) {
		super(msg);
	}
}
