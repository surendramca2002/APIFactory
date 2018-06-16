package com.citibanamex.api.cardsmaintenance.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public BadRequestException() {

	}

	public BadRequestException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return String.format("BadRequestException [errorMessage=%s]", errorMessage);
	}

}
