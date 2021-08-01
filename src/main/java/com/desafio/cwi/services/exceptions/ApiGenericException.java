package com.desafio.cwi.services.exceptions;

public class ApiGenericException  extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public ApiGenericException(String msg) {
		super(msg);
	}
	
	public ApiGenericException(String msg, Throwable caused) {
		super(msg, caused);
	}
}
