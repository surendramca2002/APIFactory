package com.citibanamex.api.cardsmaintenance.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	ErrorResponse errorResponse = null;
	List<Error> errors = null;
	Error error = null;
	
	@ExceptionHandler(value = ApplicationException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e) {
		errorResponse = new ErrorResponse();
		error = new Error();
		error.setType("Fatal");
		error.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		error.setDetails(HttpStatus.INTERNAL_SERVER_ERROR.name());
		error.setLocation("");
		error.setMoreInfo(e.getMessage());
		errors = new ArrayList<Error>();
		errors.add(error);
		errorResponse.setErrors(errors);
		logger.error("Actual Cause for Exception ", e.getMessage(), e);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = RestClientException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleClientException(RestClientException e) {
		errorResponse = new ErrorResponse();
		error = new Error();
		error.setType("Fatal");
		error.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		error.setDetails(e.getMessage());
		error.setLocation("");
		error.setMoreInfo(e.getRootCause().toString());
		errors = new ArrayList<Error>();
		errors.add(error);
		errorResponse.setErrors(errors);
		logger.error("Actual Cause for Exception ", e.getMessage(), e);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
		errorResponse = new ErrorResponse();
		error = new Error();
		error.setType("Error");
		error.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		error.setDetails(e.getErrorMessage());
		error.setLocation("");
		error.setMoreInfo(e.getMessage());
		errors = new ArrayList<Error>();
		errors.add(error);
		errorResponse.setErrors(errors);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ IllegalArgumentException.class, NullPointerException.class })
	public ResponseEntity<?> handleBadRequests(HttpServletResponse response) throws IOException {

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(NoHandlerFoundException e) {
		errorResponse = new ErrorResponse();
		error = new Error();
		error.setType("Error");
		error.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
		error.setDetails("");
		error.setLocation("" + e.getRequestURL());
		error.setMoreInfo("Resource not found " + e.getMessage());
		errors = new ArrayList<Error>();
		errors.add(error);
		errorResponse.setErrors(errors);
		logger.error("Actual Cause for Exception ", e.getMessage(), e);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleHeaders(ServletRequestBindingException e) {
		errorResponse = new ErrorResponse();
		error = new Error();
		error.setType("Error");
		error.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
		error.setDetails("Missing Parameters");
		error.setLocation("");
		error.setMoreInfo(e.getMessage());
		errors = new ArrayList<Error>();
		errors.add(error);
		errorResponse.setErrors(errors);
		logger.error("Actual Cause for Exception ", e.getMessage(), e);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
