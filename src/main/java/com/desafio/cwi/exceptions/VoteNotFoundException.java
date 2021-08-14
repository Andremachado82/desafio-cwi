package com.desafio.cwi.exceptions;

public class VoteNotFoundException  extends RuntimeException {
	public VoteNotFoundException(String msg) {
		super(msg);
	}
}
