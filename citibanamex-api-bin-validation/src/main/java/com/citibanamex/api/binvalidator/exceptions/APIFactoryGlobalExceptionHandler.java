/*
 * Copyright Banco Nacional de Mexico, S.A.
 * Integrante de Grupo Financiero Banamex.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.citibanamex.api.binvalidator.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citibanamex.api.binvalidator.model.base.BaseErrorResponse;
import com.citibanamex.api.binvalidator.util.BINValidatorConstant;

/**
 * This is a APIFactoryGlobalExceptionHandler class to handle all Exceptions
 * 
 *
 */
@RestControllerAdvice
@EnableWebMvc
public class APIFactoryGlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(APIFactoryGlobalExceptionHandler.class);

	@Autowired
	private BaseErrorResponse baseErrorResponse;
	@Autowired
	private Environment env;

	/**
	 * This method is to handle APIFactoryBadRequestException category
	 * 
	 * @param APIFactoryBadRequestException
	 * @return BaseErrorResponse
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = APIFactoryBadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(APIFactoryBadRequestException ex) {
		baseErrorResponse = setResponse(ex);
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is to handle BaseException category
	 * 
	 * @param APIFactoryServerError
	 * @return BaseErrorResponse
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = APIFactoryServerError.class)
	public ResponseEntity<?> handleBaseException(APIFactoryServerError ex) {
		baseErrorResponse = setResponse(ex);
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method is to handle APIFactoryNotFoundException category
	 * 
	 * @param APIFactoryNotFoundException
	 * @return BaseErrorResponse
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = APIFactoryDataNotFoundException.class)
	public ResponseEntity<?> handleDataNotFoundException(APIFactoryDataNotFoundException ex) {
		baseErrorResponse = setResponse(ex);
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.OK);
	}
	/**
	 * This method is to handle APIFactoryUnauthorizedException category
	 * 
	 * @param APIFactoryUnauthorizedException
	 * @return BaseErrorResponse
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = APIFactoryUnauthorizedException.class)
	public ResponseEntity<?> handleHttpHeaderException(APIFactoryUnauthorizedException ex) {
		baseErrorResponse = setResponse(ex);
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * This method is to handle APIFactoryUnauthorizedException category
	 * 
	 * @param APIFactoryUnauthorizedException
	 * @return BaseErrorResponse
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = APIFactoryFallBackException.class)
	public ResponseEntity<?> handleFallBackException(APIFactoryFallBackException ex) {
		baseErrorResponse = setResponse(ex);
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This method is to handle APIFactoryNotFoundException category
	 * 
	 * @param APIFactoryNotFoundException
	 * @return BaseErrorResponse
	 */
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<?> handleResourceNotFoundException(final NoHandlerFoundException ex) {
		baseErrorResponse.setCode(String.valueOf(HttpStatus.NOT_FOUND));
		baseErrorResponse.setDetails(env.getProperty(BINValidatorConstant.MESSAGE_RESOURCE_NOT_FOUND_DESCRIPTION));
		baseErrorResponse.setLocation(env.getProperty(BINValidatorConstant.MESSAGE_RESOURCE_NOT_FOUND));
		baseErrorResponse.setMoreInfo(env.getProperty(BINValidatorConstant.MESSAGE_EMPTY_STRING));
		baseErrorResponse.setType(env.getProperty(BINValidatorConstant.MESSAGE_ERROR));
		logger.error(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_RESPONSE)
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getCode()
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getDetails()
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getLocation());
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {

		baseErrorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
		baseErrorResponse.setDetails(env.getProperty(BINValidatorConstant.MESSAGE_MISSING_REQUEST_PARAMETER));
		baseErrorResponse.setLocation(env.getProperty(BINValidatorConstant.MESSAGE_MISSING_PARAMETER)
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + ex.getParameterName());
		baseErrorResponse.setMoreInfo(env.getProperty(BINValidatorConstant.MESSAGE_MISSING_PARAMETER_TYPE)
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + ex.getParameterType());
		baseErrorResponse.setType(env.getProperty(BINValidatorConstant.MESSAGE_ERROR));
		logger.error(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_RESPONSE)
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getCode()
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getDetails()
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getLocation());
		return new ResponseEntity<BaseErrorResponse>(baseErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param ex
	 * @return
	 */
	private BaseErrorResponse setResponse(APIFactoryBaseException ex) {
		baseErrorResponse.setCode(ex.getCode());
		baseErrorResponse.setDetails(ex.getDetails());
		baseErrorResponse.setLocation(ex.getLocation());
		baseErrorResponse.setMoreInfo(ex.getMoreInfo());
		baseErrorResponse.setType(ex.getType());
		logger.error(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_RESPONSE)
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getCode()
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getDetails()
				+ env.getProperty(BINValidatorConstant.MESSAGE_HYPHEN) + baseErrorResponse.getLocation());
		return baseErrorResponse;
	}

}
