package com.desafio.cwi.exceptions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ApiGenericException.class)
	public ResponseEntity<StandardError> objectNotFound(ApiGenericException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(PautaNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(PautaNotFoundException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(SessaoNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(SessaoNotFoundException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(UnableCpfException.class)
	public ResponseEntity<StandardError> objectNotFound(UnableCpfException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(CpfNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(CpfNotFoundException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(SessionExperidException.class)
	public ResponseEntity<StandardError> objectNotFound(SessionExperidException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.REQUEST_TIMEOUT.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(error);
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<StandardError> objectNotFound(InvalidDateException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(VotacaoNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(VotacaoNotFoundException e, HttpServletRequest request) {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(stamp);
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
