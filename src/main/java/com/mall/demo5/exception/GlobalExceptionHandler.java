package com.mall.demo5.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * private Integer errorCode; 
	 * private String errorMessage; 
	 * private LocalDateTim timestamp;
	 */
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleProductNotFoundException(ProductNotFoundException ex) {
		logger.error("ProductNotFoundException: {}", ex.getMessage(), ex);
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGlobalException(Exception ex) {
		logger.error("Unexpected Exception: {}", ex.getMessage(), ex);
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"An unexpected error occurred: " + ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}