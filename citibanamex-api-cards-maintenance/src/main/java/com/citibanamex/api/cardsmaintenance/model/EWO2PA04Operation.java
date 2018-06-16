package com.citibanamex.api.cardsmaintenance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EWO2PA04Operation {

	@JsonProperty("cardLockUnlockOvCdReqRec")
	private CardLockUnlockOvCdReqRec cardLockUnlockOvCdReqRec;

	public CardLockUnlockOvCdReqRec getCardLockUnlockOvCdReqRec() {
		return cardLockUnlockOvCdReqRec;
	}

	public void setCardLockUnlockOvCdReqRec(CardLockUnlockOvCdReqRec cardLockUnlockOvCdReqRec) {
		this.cardLockUnlockOvCdReqRec = cardLockUnlockOvCdReqRec;
	}

	@Override
	public String toString() {
		return String.format("EWO2PA04Operation [cardLockUnlockOvCdReqRec=%s]", cardLockUnlockOvCdReqRec);
	}
}
