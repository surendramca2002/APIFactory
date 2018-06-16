package com.citibanamex.api.cardplastic.binvalidation.model;

import org.springframework.stereotype.Component;

@Component
public class CardPlastic {

	private int bankIdentificationNumber;
	private String cardType;
	private String cardNetworkType;
	private String binRegistrationDate;
	private String expiryDate;
	private String cardStatus;
	private String applicationInterchangeProfileValue;
	private boolean threeDSecureFlag;
	private boolean atmAccessFlag;
	private boolean virtualCardFlag;
	private boolean chipAvailableFlag;
	private boolean branchFlag;
	private boolean ecommerceFlag;
	private boolean exchangePaymentsFlag;
	private boolean interredesFlag;
	private boolean reccurringPaymentFacilityFlag;
	private boolean phoneSalesAccessFlag;
	private boolean posFlag;
	private String clearingAndSettlementParty;
	private boolean manualImprinterUsageFlag;
	private Account account;
	private String countryCode;
	public int getBankIdentificationNumber() {
		return bankIdentificationNumber;
	}
	public void setBankIdentificationNumber(int bankIdentificationNumber) {
		this.bankIdentificationNumber = bankIdentificationNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNetworkType() {
		return cardNetworkType;
	}
	public void setCardNetworkType(String cardNetworkType) {
		this.cardNetworkType = cardNetworkType;
	}
	public String getBinRegistrationDate() {
		return binRegistrationDate;
	}
	public void setBinRegistrationDate(String binRegistrationDate) {
		this.binRegistrationDate = binRegistrationDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getApplicationInterchangeProfileValue() {
		return applicationInterchangeProfileValue;
	}
	public void setApplicationInterchangeProfileValue(String applicationInterchangeProfileValue) {
		this.applicationInterchangeProfileValue = applicationInterchangeProfileValue;
	}
	public boolean isThreeDSecureFlag() {
		return threeDSecureFlag;
	}
	public void setThreeDSecureFlag(boolean threeDSecureFlag) {
		this.threeDSecureFlag = threeDSecureFlag;
	}
	public boolean isAtmAccessFlag() {
		return atmAccessFlag;
	}
	public void setAtmAccessFlag(boolean atmAccessFlag) {
		this.atmAccessFlag = atmAccessFlag;
	}
	public boolean isVirtualCardFlag() {
		return virtualCardFlag;
	}
	public void setVirtualCardFlag(boolean virtualCardFlag) {
		this.virtualCardFlag = virtualCardFlag;
	}
	public boolean isChipAvailableFlag() {
		return chipAvailableFlag;
	}
	public void setChipAvailableFlag(boolean chipAvailableFlag) {
		this.chipAvailableFlag = chipAvailableFlag;
	}
	public boolean isBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(boolean branchFlag) {
		this.branchFlag = branchFlag;
	}
	public boolean isEcommerceFlag() {
		return ecommerceFlag;
	}
	public void setEcommerceFlag(boolean ecommerceFlag) {
		this.ecommerceFlag = ecommerceFlag;
	}
	public boolean isExchangePaymentsFlag() {
		return exchangePaymentsFlag;
	}
	public void setExchangePaymentsFlag(boolean exchangePaymentsFlag) {
		this.exchangePaymentsFlag = exchangePaymentsFlag;
	}
	public boolean isInterredesFlag() {
		return interredesFlag;
	}
	public void setInterredesFlag(boolean interredesFlag) {
		this.interredesFlag = interredesFlag;
	}
	public boolean isReccurringPaymentFacilityFlag() {
		return reccurringPaymentFacilityFlag;
	}
	public void setReccurringPaymentFacilityFlag(boolean reccurringPaymentFacilityFlag) {
		this.reccurringPaymentFacilityFlag = reccurringPaymentFacilityFlag;
	}
	public boolean isPhoneSalesAccessFlag() {
		return phoneSalesAccessFlag;
	}
	public void setPhoneSalesAccessFlag(boolean phoneSalesAccessFlag) {
		this.phoneSalesAccessFlag = phoneSalesAccessFlag;
	}
	public boolean isPosFlag() {
		return posFlag;
	}
	public void setPosFlag(boolean posFlag) {
		this.posFlag = posFlag;
	}
	public String getClearingAndSettlementParty() {
		return clearingAndSettlementParty;
	}
	public void setClearingAndSettlementParty(String clearingAndSettlementParty) {
		this.clearingAndSettlementParty = clearingAndSettlementParty;
	}
	public boolean isManualImprinterUsageFlag() {
		return manualImprinterUsageFlag;
	}
	public void setManualImprinterUsageFlag(boolean manualImprinterUsageFlag) {
		this.manualImprinterUsageFlag = manualImprinterUsageFlag;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}
