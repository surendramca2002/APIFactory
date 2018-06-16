package com.citibanamex.api.cardplastic.binvalidation.exception;

public class BinValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	private final int errorCode;
	private final String message;
	
	public BinValidationException(int error,String msg){
		this.errorCode = error;
		this.message = msg;
	}
	
	

	public int getErrorCode() {
		return errorCode;
	}
	@Override
	public String getMessage() {
		return message;
	}
	

}
