package com.citibanamex.api.cardplastic.binvalidation.model;

public class CardPlasticInformation {

	private String cardNumber;
	private String applicationInterchangeProfileValue;
	private String cardStatus;
	private String cardType;
	private String expiryDate;
	private PinInformation pinInformation;
	private AccountInformation accountInformation;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getApplicationInterchangeProfileValue() {
		return applicationInterchangeProfileValue;
	}
	public void setApplicationInterchangeProfileValue(String applicationInterchangeProfileValue) {
		this.applicationInterchangeProfileValue = applicationInterchangeProfileValue;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public PinInformation getPinInformation() {
		return pinInformation;
	}
	public void setPinInformation(PinInformation pinInformation) {
		this.pinInformation = pinInformation;
	}
	public AccountInformation getAccountInformation() {
		return accountInformation;
	}
	public void setAccountInformation(AccountInformation accountInformation) {
		this.accountInformation = accountInformation;
	}
	
}
