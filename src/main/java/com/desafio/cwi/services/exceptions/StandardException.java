package com.desafio.cwi.services.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StandardException  extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	
	private final String code;
	private final HttpStatus status;
}
