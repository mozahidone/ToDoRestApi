package com.mozahidone.backend.exceptions;

import com.mozahidone.backend.exceptions.customs.EmployeeNotFoundException;
import com.mozahidone.backend.exceptions.customs.EmployeeNotFoundException;
import com.mozahidone.backend.exceptions.payloads.ExceptionMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(
		value = {
			EmployeeNotFoundException.class
		}
	)
	public <T extends RuntimeException> ResponseEntity<ExceptionMsg> handleEmployeeNotFoundException(final T exception) {
		
		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		final ExceptionMsg exceptionMsg = new ExceptionMsg(exception.getMessage(), badRequest, LocalDateTime.now());
		// final ExceptionMsg exceptionMsg = new ExceptionMsg(exceptions.getMessage(), exceptions, badRequest, LocalDateTime.now());
		
		System.err.println(exceptionMsg);
		
		return new ResponseEntity<>(exceptionMsg, badRequest);
	}
	
	
	
}









