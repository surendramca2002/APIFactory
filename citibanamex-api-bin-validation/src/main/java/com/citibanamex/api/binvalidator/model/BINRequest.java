package com.citibanamex.api.binvalidator.model;

import org.springframework.stereotype.Component;

import com.citibanamex.api.binvalidator.model.base.BaseRequest;

@Component
public class BINRequest extends BaseRequest {

	private String bankIdentificationNumber;

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
