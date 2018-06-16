package com.citibanamex.api.cardplasticpinset.hosthandler;

import org.springframework.stereotype.Component;

import com.citibanamex.api.cardplasticpinset.model.SetCardPlasticPinResponse;


@Component
public class CardPlasticPinSetHostHandler {

public static  SetCardPlasticPinResponse getcardType(String clientId){
		 
	
    SetCardPlasticPinResponse setCardPlasticPinResponse = new SetCardPlasticPinResponse();
    setCardPlasticPinResponse.setCardNumber("123456787654321");
    setCardPlasticPinResponse.setFolioId("000001");
    setCardPlasticPinResponse.setPinChangeFlag(true);
    setCardPlasticPinResponse.setPinChangeMessage("Pin Change is Successful");
    return setCardPlasticPinResponse;
    
    
}
}
