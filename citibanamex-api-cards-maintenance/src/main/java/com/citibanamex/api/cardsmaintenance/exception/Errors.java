package com.citibanamex.api.cardsmaintenance.exception;

import java.util.ArrayList;

public class Errors {

	private ArrayList<Error> errors;

	  
	public Errors() {
		super();
	}

	public Errors(ArrayList<Error> errors) {
		super();
		this.errors = errors;
	}

	public ArrayList<Error> getErrors() {
		return this.errors;
	}

	public void setErrors(ArrayList<Error> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return String.format("Errors [errors=%s]", errors);
	}
	
}
