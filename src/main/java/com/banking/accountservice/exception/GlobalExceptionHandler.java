package com.banking.accountservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AccountNotFoundException.class )
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex){
		
		ErrorResponse response= new ErrorResponse(
									HttpStatus.NOT_FOUND.value(),
									ex.getMessage(),
									LocalDateTime.now());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);	
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientbalanceException(InsufficientBalanceException ex){
		
		ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value()
										,ex.getMessage(),
										LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleallExceptions(Exception ex){
		
		ErrorResponse errorResponse= new ErrorResponse(
										HttpStatus.INTERNAL_SERVER_ERROR.value(),
										"Something went wrong"+ex.getMessage(),
										LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
