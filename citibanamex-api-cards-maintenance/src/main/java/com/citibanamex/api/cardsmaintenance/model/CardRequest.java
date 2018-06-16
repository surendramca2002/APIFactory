package com.citibanamex.api.cardsmaintenance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardRequest {

	@JsonProperty("EWO2PA04Operation")
	private EWO2PA04Operation eWO2PA04Operation;

	public EWO2PA04Operation geteWO2PA04Operation() {
		return eWO2PA04Operation;
	}

	public void seteWO2PA04Operation(EWO2PA04Operation eWO2PA04Operation) {
		this.eWO2PA04Operation = eWO2PA04Operation;
	}

	@Override
	public String toString() {
		return String.format("CardRequest [eWO2PA04Operation=%s]", eWO2PA04Operation);
	}
}
