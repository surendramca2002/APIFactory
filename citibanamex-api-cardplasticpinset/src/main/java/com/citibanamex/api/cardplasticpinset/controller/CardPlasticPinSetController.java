package com.citibanamex.api.cardplasticpinset.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.api.cardplasticpinset.exception.CardPlasticPinSetException;
import com.citibanamex.api.cardplasticpinset.model.SetCardPlasticPinRequest;
import com.citibanamex.api.cardplasticpinset.model.SetCardPlasticPinResponse;
import com.citibanamex.api.cardplasticpinset.model.ValidateCardTypeResponse;
import com.citibanamex.api.cardplasticpinset.service.CardPlasticPinSetService;
import com.citibanamex.api.cardplasticpinset.validator.CardPlasticPinSetValidator;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
public class CardPlasticPinSetController {

	private final Logger logger  = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CardPlasticPinSetValidator cardPlasticPinSetValidator;
	@Autowired
	CardPlasticPinSetService cardPlasticPinSetService;
	@RequestMapping(value="/v1/cards/plastic/pin",produces=MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.PUT)
	@ApiOperation(value = "Retrive Card Plastic Information", notes = "Retrive Card Information")
	@ApiImplicitParams({
		//@ApiImplicitParam(name="bankIdentifationNumber", value="bankIdentifationNumber",required=true,dataType="string",paramType="path",defaultValue="545645")
		@ApiImplicitParam(name = "accept", value = "accept", required = true, dataType = "string", paramType = "header", defaultValue = "application/json"),
		@ApiImplicitParam(name = "acceptLanguage", value = "acceptLanguage", required = true, dataType = "string", paramType = "header", defaultValue = "English")
	})
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successful operation"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	
	public SetCardPlasticPinResponse setCardPlasticPinResponse(
			@RequestHeader("accept") String accept,
			@RequestHeader("acceptLanguage") String acceptLanguage,
			@RequestHeader("authorization") String authorization,
			@RequestHeader("client_id") String client_id,
			@RequestHeader("uuid") String uuid,
			@RequestBody @Valid SetCardPlasticPinRequest setCardPlasticPinDetails) throws CardPlasticPinSetException
	{
		
		HttpHeaders headers  = new HttpHeaders();
		headers.set("accept", accept);
		headers.set("acceptLanguage", acceptLanguage);
		 if(!cardPlasticPinSetValidator.isValidclientId(client_id)){
				throw new CardPlasticPinSetException(400,"invalid request");
				
			}
		 logger.info("Binvalidation card information");
		return cardPlasticPinSetService.getValidateCardType(client_id);
		 }
	

}