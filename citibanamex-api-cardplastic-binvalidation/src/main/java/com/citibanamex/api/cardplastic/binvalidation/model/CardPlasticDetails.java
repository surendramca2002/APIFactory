package com.citibanamex.api.cardplastic.binvalidation.model;

public class CardPlasticDetails {
	private String expiryDate;
	private String cardNumber;
	private AccountDetails accountDetails;
	private PinDetails pinDetails;
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public AccountDetails getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
	public PinDetails getPinDetails() {
		return pinDetails;
	}
	public void setPinDetails(PinDetails pinDetails) {
		this.pinDetails = pinDetails;
	}
	
}
