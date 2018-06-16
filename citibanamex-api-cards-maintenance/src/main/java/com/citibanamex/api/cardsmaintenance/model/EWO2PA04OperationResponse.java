package com.citibanamex.api.cardsmaintenance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EWO2PA04OperationResponse {
	
	@JsonProperty("cardLockUnlockOvCdResRec")
	private CardLockUnlockOvCdResRec cardLockUnlockOvCdResRec;

	public EWO2PA04OperationResponse() {
		super();
	}
	public CardLockUnlockOvCdResRec getCardLockUnlockOvCdResRec() {
		return cardLockUnlockOvCdResRec;
	}

	public void setCardLockUnlockOvCdResRec(CardLockUnlockOvCdResRec cardLockUnlockOvCdResRec) {
		this.cardLockUnlockOvCdResRec = cardLockUnlockOvCdResRec;
	}
	@Override
	public String toString() {
		return String.format("EWO2PA04OperationResponse [cardLockUnlockOvCdResRec=%s]", cardLockUnlockOvCdResRec);
	}
	
}
