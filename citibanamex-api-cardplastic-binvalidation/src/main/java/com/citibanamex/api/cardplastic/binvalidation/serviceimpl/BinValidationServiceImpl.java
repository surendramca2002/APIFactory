package com.citibanamex.api.cardplastic.binvalidation.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citibanamex.api.cardplastic.binvalidation.hosthandler.BinValidationHostHandler;
import com.citibanamex.api.cardplastic.binvalidation.model.ValidateCardTypeResponse;
import com.citibanamex.api.cardplastic.binvalidation.service.BinValidationService;
@Service
public class BinValidationServiceImpl implements BinValidationService  {
	@Autowired
	BinValidationHostHandler binValidationHostHandler;

	public ValidateCardTypeResponse getValidateCardType(String clientId) {
		
		return binValidationHostHandler.getcardType(clientId);
	}

}
