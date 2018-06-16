package com.citibanamex.api.cardplasticpinset.model;

import org.springframework.stereotype.Component;

@Component
public class GetCardPlasticResponse {

	private CardPlasticInformation cardPlasticInformation;

	public CardPlasticInformation getCardPlasticInformation() {
		return cardPlasticInformation;
	}

	public void setCardPlasticInformation(CardPlasticInformation cardPlasticInformation) {
		this.cardPlasticInformation = cardPlasticInformation;
	}
	
}
