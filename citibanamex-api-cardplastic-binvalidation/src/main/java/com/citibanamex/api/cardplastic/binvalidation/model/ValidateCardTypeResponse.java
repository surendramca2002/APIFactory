package com.citibanamex.api.cardplastic.binvalidation.model;

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
