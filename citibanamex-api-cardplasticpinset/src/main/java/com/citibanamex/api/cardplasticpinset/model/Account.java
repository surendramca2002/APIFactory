package com.citibanamex.api.cardplasticpinset.model;

import org.springframework.stereotype.Component;

@Component
public class Account {

	private String banckName;
	private String productType;
	public String getBanckName() {
		return banckName;
	}
	public void setBanckName(String bankName) {
		this.banckName = bankName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
}
