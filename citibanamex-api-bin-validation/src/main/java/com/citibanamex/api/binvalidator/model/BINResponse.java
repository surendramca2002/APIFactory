package com.citibanamex.api.binvalidator.model;

import org.springframework.stereotype.Component;

@Component
public class BINResponse {

	private String bankName;
	private String cardNetworkType;
	private String bankIdentificationNumber;
	private String cardType;
	private String countryCode;
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the cardNetworkType
	 */
	public String getCardNetworkType() {
		return cardNetworkType;
	}

	/**
	 * @param cardNetworkType
	 *            the cardNetworkType to set
	 */
	public void setCardNetworkType(String cardNetworkType) {
		this.cardNetworkType = cardNetworkType;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the bankIdentificationNumber
	 */
	public String getBankIdentificationNumber() {
		return bankIdentificationNumber;
	}

	/**
	 * @param bankIdentificationNumber the bankIdentificationNumber to set
	 */
	public void setBankIdentificationNumber(String bankIdentificationNumber) {
		this.bankIdentificationNumber = bankIdentificationNumber;
	}

}
