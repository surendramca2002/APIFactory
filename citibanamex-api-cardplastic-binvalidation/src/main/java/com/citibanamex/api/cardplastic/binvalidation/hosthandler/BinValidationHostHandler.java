package com.citibanamex.api.cardplastic.binvalidation.hosthandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citibanamex.api.cardplastic.binvalidation.model.Account;
import com.citibanamex.api.cardplastic.binvalidation.model.CardPlastic;
import com.citibanamex.api.cardplastic.binvalidation.model.ValidateCardTypeResponse;
@Component
public class BinValidationHostHandler {
	 @Autowired
	 ValidateCardTypeResponse validateCardTypeResponse;
	 @Autowired
	 CardPlastic cardPlastic;
	 @Autowired
	 Account account;
	 
public ValidateCardTypeResponse getcardType(String clientId){
	
	 
	 account.setBanckName("GOLD");
	 account.setProductType("CAPITAL ABC CREDITO");
	 cardPlastic.setBankIdentificationNumber(123456);
	 cardPlastic.setCardType("CREDIT");
	 cardPlastic.setCardNetworkType("VISA");
	 cardPlastic.setBinRegistrationDate("01-jan-2000");
	 cardPlastic.setExpiryDate("01-jan-2000");
	 cardPlastic.setCardStatus("ACTIVE");
	 cardPlastic.setApplicationInterchangeProfileValue("12354");
	 cardPlastic.setThreeDSecureFlag(true);
	 cardPlastic.setAtmAccessFlag(true);
	 cardPlastic.setVirtualCardFlag(true);
	 cardPlastic.setChipAvailableFlag(true);
	 cardPlastic.setBranchFlag(true);
	 cardPlastic.setEcommerceFlag(true);
	 cardPlastic.setExchangePaymentsFlag(true);
	 cardPlastic.setInterredesFlag(true);
	 cardPlastic.setReccurringPaymentFacilityFlag(true);
	 cardPlastic.setPhoneSalesAccessFlag(true);
	 cardPlastic.setPosFlag(true);
	 cardPlastic.setClearingAndSettlementParty("EGLOBAL");
	 cardPlastic.setManualImprinterUsageFlag(true);
	 cardPlastic.setCountryCode("MX");
	 cardPlastic.setAccount(account);
	validateCardTypeResponse.setCardPlastic(cardPlastic);
	
	return validateCardTypeResponse;
	 
}
}
