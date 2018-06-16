package com.citibanamex.api.cardsmaintenance.model;

public class CardStatus {

	private String cardStatus;

	public CardStatus() {

	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	@Override
	public String toString() {
		return String.format("CardStatus [cardStatus=%s]", cardStatus);
	}

}
