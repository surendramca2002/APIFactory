package com.citibanamex.api.cardplastic.binvalidation.service;

import com.citibanamex.api.cardplastic.binvalidation.model.ValidateCardTypeResponse;

public interface BinValidationService {
public ValidateCardTypeResponse getValidateCardType(String clientId); 
	
}
