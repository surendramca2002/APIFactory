package com.citibanamex.api.cardplasticpinset.model;

import org.springframework.stereotype.Component;

@Component
public class AccountDetails {
private int customerId;
private String accountNumber;
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

}
