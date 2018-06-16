package com.citibanamex.api.cardplastic.binvalidation.validator;

import org.springframework.stereotype.Component;

@Component
public class BinValidationValidator {
private boolean idFlag=false;
public boolean isValidId(String bankIdentificationNumber){
	idFlag=false;
	if(bankIdentificationNumber.length()>=8 && bankIdentificationNumber.length()<=11){
		idFlag=true;
	System.out.println(idFlag);
	}
	return idFlag;
}
}
