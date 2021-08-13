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
	final String  FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
	
	@ExceptionHandler(ApiGenericException.class)
	public ResponseEntity<StandardError> objectNotFound(ApiGenericException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(PautaNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(PautaNotFoundException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(SessaoNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(SessaoNotFoundException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(UnableCpfException.class)
	public ResponseEntity<StandardError> objectNotFound(UnableCpfException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(CpfNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(CpfNotFoundException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(SessionExperidException.class)
	public ResponseEntity<StandardError> objectNotFound(SessionExperidException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.REQUEST_TIMEOUT.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(error);
	}
	
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<StandardError> objectNotFound(InvalidDateException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(VotacaoNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(VotacaoNotFoundException e, HttpServletRequest request) {
		var stamp = new Timestamp(System.currentTimeMillis());
		String dateFormat = new SimpleDateFormat(FORMAT_DATE_TIME).format(stamp);
		
		var error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), dateFormat);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
