package com.desafio.cwi.exceptions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio.cwi.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
