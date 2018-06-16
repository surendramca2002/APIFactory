package com.citibanamex.api.cardplasticpinset.serviceimpl;



import org.springframework.stereotype.Service;

import com.citibanamex.api.cardplasticpinset.hosthandler.CardPlasticPinSetHostHandler;
import com.citibanamex.api.cardplasticpinset.model.SetCardPlasticPinResponse;
import com.citibanamex.api.cardplasticpinset.service.CardPlasticPinSetService;
@Service
public class CardPlasticPinSetServiceImpl implements CardPlasticPinSetService {


	public SetCardPlasticPinResponse getValidateCardType(String clientId) {
		
		return CardPlasticPinSetHostHandler.getcardType(clientId);
	}

}
