package com.citibanamex.api.cardplasticpinset.exception;

public class CardPlasticPinSetException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String message;
	
	public CardPlasticPinSetException(int error,String msg){
		this.errorCode = error;
		this.message = msg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
