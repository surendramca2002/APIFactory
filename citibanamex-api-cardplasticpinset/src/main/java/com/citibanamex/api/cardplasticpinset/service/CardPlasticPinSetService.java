package com.citibanamex.api.cardplasticpinset.service;

import org.springframework.stereotype.Service;

import com.citibanamex.api.cardplasticpinset.model.SetCardPlasticPinResponse;
import com.citibanamex.api.cardplasticpinset.model.ValidateCardTypeResponse;


public interface CardPlasticPinSetService {

	public SetCardPlasticPinResponse getValidateCardType(String clientId); 
}
