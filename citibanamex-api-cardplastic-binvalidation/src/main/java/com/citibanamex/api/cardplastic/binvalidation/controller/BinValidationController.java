package com.citibanamex.api.cardplastic.binvalidation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.api.cardplastic.binvalidation.exception.BinValidationException;
import com.citibanamex.api.cardplastic.binvalidation.model.ValidateCardTypeResponse;
import com.citibanamex.api.cardplastic.binvalidation.service.BinValidationService;
import com.citibanamex.api.cardplastic.binvalidation.validator.BinValidationValidator;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
public class BinValidationController {
	private final Logger logger  = LoggerFactory.getLogger(this.getClass());

@Autowired
BinValidationValidator binValidationValidator;
@Autowired
BinValidationService binValidationService;
@RequestMapping(value="/cards/plastic/{bankIdentificationNumber}/bin/validate",produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
@ApiOperation(value = "Retrive Card Plastic Information", notes = "Retrive Card Information")
@ApiImplicitParams({
	@ApiImplicitParam(name = "accept", value = "accept", required = true, dataType = "string", paramType = "header", defaultValue = "application/json"),
	@ApiImplicitParam(name = "acceptLanguage", value = "acceptLanguage", required = true, dataType = "string", paramType = "header", defaultValue = "English")
	//@ApiImplicitParam(name="bankIdentifationNumber", value="bankIdentifationNumber",required=true,dataType="string",paramType="path",defaultValue="545645")
})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
 public ValidateCardTypeResponse validateCardTypeResponse(@RequestHeader("authorization") String authorization,
		 @RequestHeader("uuid") String uuid,
		 @RequestHeader("accept") String accept,
		 @RequestHeader("clientId") String clientId,
		 @RequestHeader("acceptLanguage")String acceptLanguage,
		 @PathVariable("bankIdentificationNumber") String bankIdentificationNumber) throws BinValidationException
 {
	
	HttpHeaders headers  = new HttpHeaders();
	headers.set("accept", accept);
	headers.set("acceptLanguage", acceptLanguage);
 if(!binValidationValidator.isValidId(bankIdentificationNumber)){
		throw new BinValidationException(500, "invalid bankIdentificationNumber");
		 
	}
 logger.info("Binvalidation card information");
return binValidationService.getValidateCardType(bankIdentificationNumber);
 }
}
