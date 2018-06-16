package com.citibanamex.api.cardplastic.binvalidation.model;

public class PinDetails {
	private String newPinNumber;
	private String oldPinNumber;
	private boolean pinChangeFlag;
	private String pinChangeMessage;
	private String newPinConfirmation;
	public String getNewPinConfirmation() {
		return newPinConfirmation;
	}
	public void setNewPinConfirmation(String newPinConfirmation) {
		this.newPinConfirmation = newPinConfirmation;
	}
	public String getNewPinNumber() {
		return newPinNumber;
	}
	public void setNewPinNumber(String newPinNumber) {
		this.newPinNumber = newPinNumber;
	}
	public String getOldPinNumber() {
		return oldPinNumber;
	}
	public void setOldPinNumber(String oldPinNumber) {
		this.oldPinNumber = oldPinNumber;
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
