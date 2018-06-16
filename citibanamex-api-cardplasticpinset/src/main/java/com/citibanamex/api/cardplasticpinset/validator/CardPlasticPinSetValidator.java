package com.citibanamex.api.cardplasticpinset.validator;

import org.springframework.stereotype.Component;

@Component
public class CardPlasticPinSetValidator {

	private static boolean clientIdFlag;
	public static boolean isValidclientId(String clientId){
		if(null!=clientId&&!clientId.equals("")){
			clientIdFlag=true;
			
		}
		return clientIdFlag;
	}
}
