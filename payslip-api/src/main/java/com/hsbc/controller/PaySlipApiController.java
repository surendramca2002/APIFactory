/**
 * 
 */
package com.hsbc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.model.PaySlip;
import com.hsbc.servcie.PaySlipApiService;

@RestController
@RequestMapping("/v1")
public class PaySlipApiController {
	
	@Autowired
	private PaySlipApiService paySlipApiService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaySlipApiController.class);
	
	@GetMapping(path="/hsbc/employee/{id}/paySlip", produces={MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> reteivePaySlip(@PathVariable(value="id", required=true) Long empId){
		
		logger.info("==============Inside Controller==================");
		
		PaySlip paySlip = paySlipApiService.reteivePaySlip(empId);
		
		logger.info("Returned from PaySlip Service {}", paySlip);
		
		if(null != paySlip){
			logger.info("Success Response");
			return new ResponseEntity<PaySlip>(paySlip, HttpStatus.OK);
		}
		else{
			logger.info("Failure Response");
			String errorResponse = "{\"errorMsg\":\"Please provide valid Employee ID\",\"errorCode\":\"404\"}";
			return new ResponseEntity<String>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
	}

}
