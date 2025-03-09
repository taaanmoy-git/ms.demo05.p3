package com.mall.demo5.exception;

import java.time.LocalDateTime;

public class ErrorInfo {
	private Integer errorCode;
	private String errorMessage;
	private LocalDateTime timestamp;
	
	public ErrorInfo() {
		super();
		
	}

	public ErrorInfo(Integer errorCode, String errorMessage, LocalDateTime timestamp) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
	}

	
	public Integer getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
