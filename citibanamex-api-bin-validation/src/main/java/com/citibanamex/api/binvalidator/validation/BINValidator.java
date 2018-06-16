/**
 * 
 */
package com.citibanamex.api.binvalidator.validation;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.citibanamex.api.binvalidator.exceptions.APIFactoryBadRequestException;
import com.citibanamex.api.binvalidator.exceptions.APIFactoryBaseException;
import com.citibanamex.api.binvalidator.exceptions.APIFactoryUnauthorizedException;
import com.citibanamex.api.binvalidator.model.BINRequest;
import com.citibanamex.api.binvalidator.util.BINValidatorConstant;

/**
 * @author na85517
 *
 */
@Component
public class BINValidator {
	
	@Autowired
	private Environment env;
	private static final Logger log = LoggerFactory.getLogger(BINValidator.class);
	
	public boolean isValidCardNumber(String inputParam) {
		boolean result = false;
		Pattern pattern = Pattern.compile("^[0-9]{8}$");
		result = pattern.matcher(inputParam).matches();
		return result;
	}

	public boolean isValidHeader(BINRequest binRequest) throws APIFactoryBaseException {
		
		boolean result = false;
		result = isValidAuthorizationHeader(binRequest.getAuthorization());
		result = isValidUUIDHeader(binRequest.getUuid());
		result = isValidAcceptLanguageHeader(binRequest.getAcceptLanguage());
		result = isValidClientIdHeader(binRequest.getClientId());
		result = isValidAcceptHeader(binRequest.getAccept());
		
		return result;
	}
	public boolean isValidAuthorizationHeader(String authorization) throws APIFactoryUnauthorizedException {
		
		if(StringUtils.isEmpty(authorization) )
		{
			log.error("*****APIFactoryUnauthorizedException*****");
			throw new APIFactoryUnauthorizedException(env.getProperty(BINValidatorConstant.RESPONSE_MESSAGE_ERROR), HttpStatus.UNAUTHORIZED.toString(), 
					env.getProperty(BINValidatorConstant.MESSAGE_UNAUTHORIZED_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_AUTHORIZATION),
					env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		
		return true;
	}

	public boolean isValidUUIDHeader(String uuid) throws APIFactoryBadRequestException {
		
		if(StringUtils.isEmpty(uuid) )
		{
			badRequestException(env.getProperty(BINValidatorConstant.MESSAGE_UUID_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_UUID), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		return true;
	}

	public boolean isValidAcceptHeader(String accept) throws APIFactoryBadRequestException {

		if(StringUtils.isEmpty(accept) )
		{
			badRequestException(env.getProperty(BINValidatorConstant.MESSAGE_ACCEPT_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_ACCEPT), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		return true;
	}

	public boolean isValidAcceptLanguageHeader(String acceptLanguage) throws APIFactoryBadRequestException {

		if(StringUtils.isEmpty(acceptLanguage) )
		{
			badRequestException(env.getProperty(BINValidatorConstant.MESSAGE_ACCEPT_LANGUAGE_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_ACCEPT_LANGUAGE), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		return true;
	}

	public boolean isValidClientIdHeader(String clientId) throws APIFactoryBadRequestException {
		
		if(StringUtils.isEmpty(clientId) )
		{
			badRequestException(env.getProperty(BINValidatorConstant.MESSAGE_CLIENT_ID_DESCRIPTION), env.getProperty(BINValidatorConstant.MESSAGE_CLIENT_ID), env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		}
		return true;
	}
	
	/**
	 * @throws APIFactoryBadRequestException
	 */
	private void badRequestException(String details, String location, String moreInfo) throws APIFactoryBadRequestException {
		log.error("*****APIFactoryUnauthorizedException*****");
		throw new APIFactoryBadRequestException(env.getProperty(BINValidatorConstant.RESPONSE_MESSAGE_ERROR), HttpStatus.BAD_REQUEST.toString(), 
				details, location, moreInfo);
	}

}
