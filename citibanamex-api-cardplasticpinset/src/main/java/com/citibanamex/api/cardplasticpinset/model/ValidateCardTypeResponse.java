package com.citibanamex.api.cardplasticpinset.model;

import org.springframework.stereotype.Component;

@Component
public class ValidateCardTypeResponse {
private CardPlastic cardPlastic;

public CardPlastic getCardPlastic() {
	return cardPlastic;
}

public void setCardPlastic(CardPlastic cardPlastic) {
	this.cardPlastic = cardPlastic;
}

}
