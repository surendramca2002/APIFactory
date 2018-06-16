/*
 * Copyright Banco Nacional de Mexico, S.A.
 * Integrante de Grupo Financiero Banamex.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.citibanamex.api.binvalidator.controller;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.citibanamex.api.binvalidator.exceptions.APIFactoryBaseException;
import com.citibanamex.api.binvalidator.exceptions.APIFactoryDataNotFoundException;
import com.citibanamex.api.binvalidator.exceptions.APIFactoryFallBackException;
import com.citibanamex.api.binvalidator.exceptions.APIFactoryServerError;
import com.citibanamex.api.binvalidator.model.BINRequest;
import com.citibanamex.api.binvalidator.model.BINResponse;
import com.citibanamex.api.binvalidator.service.IBINValidateService;
import com.citibanamex.api.binvalidator.util.BINValidatorConstant;
import com.citibanamex.api.binvalidator.validation.BINValidator;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * 
 */
@RestController
@RequestMapping("/api/v1")
@SwaggerDefinition(info = @Info(title = "Card Plastic Microservice", description = "APIs for Card Plastic Microservice", version = "1.0.0"), host = "127.0.0.1", schemes = {
		SwaggerDefinition.Scheme.HTTPS }, basePath = "/api", produces = { "application/json" })
public class BINController {

	@Autowired
	private IBINValidateService iBINValidateService;
	@Autowired
	private BINValidator binValidator;
	@Autowired
	private BINResponse binResponse;
	@Autowired
	private BINRequest binRequest;
	@Autowired
	private Environment env;
	
	private static final Logger log = LoggerFactory.getLogger(BINController.class);

	/**
	 * @param bankIdentificationNumber
	 * @return
	 * @throws APIFactoryBaseException
	 */
	@GetMapping(value = "/cardPlastic/{bankIdentificationNumber}/bin/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "BINValidation", nickname = "This API is used to get the Card Plastic information based on BIN")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bankIdentificationNumber", value = "Bank Identification Number", required = true, dataType = "integer", paramType = "path"),
			@ApiImplicitParam(name = "Authorization", paramType = "header", value = "Bearer{Access_Token}", required = true, dataType = "String"),
			@ApiImplicitParam(name = "uuid", paramType = "header", value = "123e4567-e89b-12d3-a456-426655440000", required = true, dataType = "String"),
			@ApiImplicitParam(name = "Accept", paramType = "header", value = "application/json", required = true, dataType = "String"),
			@ApiImplicitParam(name = "client_id", paramType = "header", value = "123e4567-e89b-12d3-a456-426655440000", required = true, dataType = "String"),
			@ApiImplicitParam(name = "Accept-Language", paramType = "header", value = "en-US", required = true, dataType = "String")})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 400, message = "BadRequest"),
			@ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<?> validateBIN(@RequestHeader(value = "Authorization") String authorization,
			@RequestHeader(value = "uuid") String uuid, @RequestHeader(value = "Accept") String accept,
			@RequestHeader(value = "Accept-Language") String acceptLanguage,
			@RequestHeader(value = "client_id") String clientId,
			@PathVariable(value = "bankIdentificationNumber") String bankIdentificationNumber) throws APIFactoryBaseException {
		
		log.info("In BINController..." + new Date().getTime());
		
		HashMap<String, BINResponse> cardPlasticResponse=null;
		
		binRequest.setAuthorization(authorization);
		binRequest.setAccept(accept);
		binRequest.setUuid(uuid);
		binRequest.setAcceptLanguage(acceptLanguage);
		binRequest.setClientId(clientId);
		binRequest.setBankIdentificationNumber(bankIdentificationNumber);
		
		binValidator.isValidHeader(binRequest);
		
		try {
			binResponse = iBINValidateService.validateBIN(binRequest);
			
			cardPlasticResponse = new HashMap<>();
			cardPlasticResponse.put(env.getProperty(BINValidatorConstant.MESSAGE_CARD), binResponse);
			
		} catch (HttpClientErrorException e) {
			log.error("*****APIFactoryDataNotFoundException*****");
			throw new APIFactoryDataNotFoundException(env.getProperty(BINValidatorConstant.RESPONSE_MESSAGE_ERROR), HttpStatus.NOT_FOUND.toString(),
					env.getProperty(BINValidatorConstant.MESSAGE_NOT_FOUND),
					env.getProperty(BINValidatorConstant.MESSAGE_NOT_FOUND_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		} catch (HystrixRuntimeException e) {
			log.error("*****APIFactoryDataNotFoundException*****");
		if(null != e.getCause() && null != e.getCause().getMessage() && e.getCause().getMessage().equals(env.getProperty(BINValidatorConstant.MESSAGE_CLIENT_CARD_NOT_FOUND))){
			throw new APIFactoryDataNotFoundException(env.getProperty(BINValidatorConstant.RESPONSE_MESSAGE_ERROR), HttpStatus.NOT_FOUND.toString(),
					env.getProperty(BINValidatorConstant.MESSAGE_NOT_FOUND),
					env.getProperty(BINValidatorConstant.MESSAGE_NOT_FOUND_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}else
		{
			throw new APIFactoryFallBackException(env.getProperty(BINValidatorConstant.RESPONSE_MESSAGE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
					env.getProperty(BINValidatorConstant.RESPONSE_FALLBACK_ERROR),
					env.getProperty(BINValidatorConstant.RESPONSE_FALLBACK_ERROR_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		
		}catch (Exception e) {
			log.error("*****APIFactoryServerError*****");
			throw new APIFactoryServerError(env.getProperty(BINValidatorConstant.RESPONSE_MESSAGE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
					env.getProperty(BINValidatorConstant.MESSAGE_INTERNAL_SERVER_ERROR),
					env.getProperty(BINValidatorConstant.MESSAGE_INTERNAL_SERVER_ERROR_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		log.info("Exiting BINController..." + new Date().getTime());
		return new ResponseEntity<>(cardPlasticResponse, HttpStatus.OK);
	}
}
