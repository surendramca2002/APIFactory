package com.citibanamex.api.cardsmaintenance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardResponse {

	@JsonProperty("EWO2PA04OperationResponse")
	private EWO2PA04OperationResponse EWO2PA04OperationResponse;

	public CardResponse() {
		super();
	}

	public EWO2PA04OperationResponse getEWO2PA04OperationResponse() {
		return EWO2PA04OperationResponse;
	}

	public void setEWO2PA04OperationResponse(EWO2PA04OperationResponse eWO2PA04OperationResponse) {
		EWO2PA04OperationResponse = eWO2PA04OperationResponse;
	}

	@Override
	public String toString() {
		return String.format("CardResponse [EWO2PA04OperationResponse=%s]", EWO2PA04OperationResponse);
	}
}