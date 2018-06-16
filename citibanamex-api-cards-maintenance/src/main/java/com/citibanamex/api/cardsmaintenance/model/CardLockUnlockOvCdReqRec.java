package com.citibanamex.api.cardsmaintenance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardLockUnlockOvCdReqRec {

	@JsonProperty("afewsMessageID")
	private String afewsMessageID;

	@JsonProperty("sourceSystem")
	private String sourceSystem;

	@JsonProperty("requestDateDDMMYYYY")
	private String requestDateDDMMYYYY;

	@JsonProperty("requestTimeHHMMSS")
	private String requestTimeHHMMSS;

	@JsonProperty("requestTimeMSSSSSSS")
	private String requestTimeMSSSSSSS;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("cICControlID")
	private String cICControlID;

	@JsonProperty("cRUDIndicator")
	private String cRUDIndicator;

	@JsonProperty("cardNumber")
	private String cardNumber;

	@JsonProperty("overrideCd")
	private String overrideCd;

	@JsonProperty("applyTillDateDDMMYYYY")
	private String applyTillDateDDMMYYYY;

	public String getAfewsMessageID() {
		return afewsMessageID;
	}

	public void setAfewsMessageID(String afewsMessageID) {
		this.afewsMessageID = afewsMessageID;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getRequestDateDDMMYYYY() {
		return requestDateDDMMYYYY;
	}

	public void setRequestDateDDMMYYYY(String requestDateDDMMYYYY) {
		this.requestDateDDMMYYYY = requestDateDDMMYYYY;
	}

	public String getRequestTimeHHMMSS() {
		return requestTimeHHMMSS;
	}

	public void setRequestTimeHHMMSS(String requestTimeHHMMSS) {
		this.requestTimeHHMMSS = requestTimeHHMMSS;
	}

	public String getRequestTimeMSSSSSSS() {
		return requestTimeMSSSSSSS;
	}

	public void setRequestTimeMSSSSSSS(String requestTimeMSSSSSSS) {
		this.requestTimeMSSSSSSS = requestTimeMSSSSSSS;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getcICControlID() {
		return cICControlID;
	}

	public void setcICControlID(String cICControlID) {
		this.cICControlID = cICControlID;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getOverrideCd() {
		return overrideCd;
	}

	public void setOverrideCd(String overrideCd) {
		this.overrideCd = overrideCd;
	}

	public String getApplyTillDateDDMMYYYY() {
		return applyTillDateDDMMYYYY;
	}

	public void setApplyTillDateDDMMYYYY(String applyTillDateDDMMYYYY) {
		this.applyTillDateDDMMYYYY = applyTillDateDDMMYYYY;
	}

	public String getcRUDIndicator() {
		return cRUDIndicator;
	}

	public void setcRUDIndicator(String cRUDIndicator) {
		this.cRUDIndicator = cRUDIndicator;
	}

	@Override
	public String toString() {
		return String.format(
				"CardLockUnlockOvCdReqRec [afewsMessageID=%s, sourceSystem=%s, requestDateDDMMYYYY=%s, requestTimeHHMMSS=%s, requestTimeMSSSSSSS=%s, countryCode=%s, cICControlID=%s, cRUDIndicator=%s, cardNumber=%s, overrideCd=%s, applyTillDateDDMMYYYY=%s]",
				afewsMessageID, sourceSystem, requestDateDDMMYYYY, requestTimeHHMMSS, requestTimeMSSSSSSS, countryCode,
				cICControlID, cRUDIndicator, cardNumber, overrideCd, applyTillDateDDMMYYYY);
	}

}
