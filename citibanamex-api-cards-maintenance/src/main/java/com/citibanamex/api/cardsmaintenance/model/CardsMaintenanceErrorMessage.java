package com.citibanamex.api.cardsmaintenance.model;

public enum CardsMaintenanceErrorMessage {
	MISSING_PARAMETERS_REQUIRED("Missing Parameters are required"), INVALID_CRUD_INDICATOR("Invalid Crud Indicator"), INVALID_CARD_NUMBER("Invalid Card Number"), INVALID_BLOCK_TILL_DATE("Invalid blockTillDate"), 
	CARD_NUMBER_LENGHT_SHOULD_BE_16("card number should be of 16 digits"), DATE_MUST_BE_GREATER_THAN_TODAYS_DATE("Date must be greater than today's Date"),

	BACK_DATE_IS_NOT_ALLOWED_FOR_BLOCK_CARD("Back Date is not allowed for blocking Card");

	private String value;

	private CardsMaintenanceErrorMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
