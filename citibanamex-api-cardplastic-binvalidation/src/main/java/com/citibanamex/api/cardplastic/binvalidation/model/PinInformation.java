package com.citibanamex.api.cardplastic.binvalidation.model;

public class PinInformation {
	private String cardPinOfflineStatus;
	private int pinFailedCount;
	private String pinOfflineEnabledDate;
	private String pinSynchronizationStatus;
	public String getCardPinOfflineStatus() {
		return cardPinOfflineStatus;
	}
	public void setCardPinOfflineStatus(String cardPinOfflineStatus) {
		this.cardPinOfflineStatus = cardPinOfflineStatus;
	}
	public int getPinFailedCount() {
		return pinFailedCount;
	}
	public void setPinFailedCount(int pinFailedCount) {
		this.pinFailedCount = pinFailedCount;
	}
	public String getPinOfflineEnabledDate() {
		return pinOfflineEnabledDate;
	}
	public void setPinOfflineEnabledDate(String pinOfflineEnabledDate) {
		this.pinOfflineEnabledDate = pinOfflineEnabledDate;
	}
	public String getPinSynchronizationStatus() {
		return pinSynchronizationStatus;
	}
	public void setPinSynchronizationStatus(String pinSynchronizationStatus) {
		this.pinSynchronizationStatus = pinSynchronizationStatus;
	}
	
}
