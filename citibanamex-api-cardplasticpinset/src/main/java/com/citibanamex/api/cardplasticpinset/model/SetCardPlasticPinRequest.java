package com.citibanamex.api.cardplasticpinset.model;

import org.springframework.stereotype.Component;

@Component
public class SetCardPlasticPinRequest {

	private CardPlasticDetails cardPlasticDetails;

	public CardPlasticDetails getCardPlasticDetails() {
		return cardPlasticDetails;
	}

	public void setCardPlasticDetails(CardPlasticDetails cardPlasticDetails) {
		this.cardPlasticDetails = cardPlasticDetails;
	}
	
}
