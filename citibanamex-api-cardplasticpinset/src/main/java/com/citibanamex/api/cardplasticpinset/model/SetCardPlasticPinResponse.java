package com.citibanamex.api.cardplasticpinset.model;

import org.springframework.stereotype.Component;

@Component
public class SetCardPlasticPinResponse {
	private String cardNumber;
	private String folioId;
	private boolean pinChangeFlag;
	private String pinChangeMessage;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getFolioId() {
		return folioId;
	}
	public void setFolioId(String folioId) {
		this.folioId = folioId;
	}
	public boolean isPinChangeFlag() {
		return pinChangeFlag;
	}
	public void setPinChangeFlag(boolean pinChangeFlag) {
		this.pinChangeFlag = pinChangeFlag;
	}
	public String getPinChangeMessage() {
		return pinChangeMessage;
	}
	public void setPinChangeMessage(String pinChangeMessage) {
		this.pinChangeMessage = pinChangeMessage;
	}
	
	
}
