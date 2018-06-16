/**
 * 
 */
package com.citibanamex.api.cardsmaintenance.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citibanamex.api.cardsmaintenance.exception.ApplicationException;
import com.citibanamex.api.cardsmaintenance.exception.BadRequestException;
import com.citibanamex.api.cardsmaintenance.exception.Errors;
import com.citibanamex.api.cardsmaintenance.model.CardsMaintenanceConstant;
import com.citibanamex.api.cardsmaintenance.model.CardsMaintenanceErrorMessage;
import com.citibanamex.api.cardsmaintenance.service.CardsMaintenanceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class CardsMaintenanceController {

	private static final Logger logger = LoggerFactory.getLogger(CardsMaintenanceController.class);

	private Map<String, String> reqData = null;

	@Autowired
	private CardsMaintenanceService cardsMaintenanceService;

	@RequestMapping(value = "/v1/creditCards/cardServicing/{cardId}/block", method = RequestMethod.POST)
	@ApiOperation(value = "blockCard", nickname = "Temporarily block the credit card")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<?> blockCard(@PathVariable(value = "cardId", required = true) String cardNumber,
			@RequestParam(value = "blockTillDate") String blockTillDate,
			@RequestParam(value = "crudIndicator") String crudIndicator,
			@RequestHeader(value = "client_id") String clientId, @RequestHeader(value = "Authorization") String auth,
			@RequestHeader(value = "uuid") String uuid, @RequestHeader(value = "Content-Type") String contentType,
			@RequestHeader(value = "Accept") String accept)
					throws ApplicationException, BadRequestException, ParseException {
		logger.info("blockCard()");

		ResponseEntity<?> blockCardResponse = null;

		if ((blockTillDate == null || blockTillDate.isEmpty()) || (crudIndicator == null || crudIndicator.isEmpty())
				|| (clientId == null || clientId.isEmpty()) || (auth == null || auth.isEmpty())
				|| (uuid == null || uuid.isEmpty()) || (contentType == null || contentType.isEmpty())
				|| (accept == null || accept.isEmpty())) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.MISSING_PARAMETERS_REQUIRED.getValue());
		}

		if (!crudIndicator.matches("^[L]$")) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.INVALID_CRUD_INDICATOR.getValue());
		}

		String regex = "\\d+";
		if (!cardNumber.matches(regex)) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.INVALID_CARD_NUMBER.getValue());
		}
		
		boolean isValidDate = validateDate(blockTillDate);
		if (!isValidDate) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.INVALID_BLOCK_TILL_DATE.getValue());
		}

		if (cardNumber != "" && cardNumber != null) {
			if (cardNumber.length() == 16) {
				try {
					reqData = new HashMap<String, String>();
					reqData.put("uuid", uuid);
					reqData.put("client_id", clientId);
					reqData.put("Authorization", auth);
					blockCardResponse = cardsMaintenanceService.doBlockUnblockCard(crudIndicator, cardNumber,
							blockTillDate, reqData);
				} catch (Exception e) {
					logger.error("Inside CardsMaintenanceController::::: ", e);
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				throw new BadRequestException(CardsMaintenanceErrorMessage.CARD_NUMBER_LENGHT_SHOULD_BE_16.getValue());
			}

		} else {
			throw new BadRequestException(CardsMaintenanceErrorMessage.MISSING_PARAMETERS_REQUIRED.getValue());
		}

		if (blockCardResponse.getBody() instanceof Errors) {
			return new ResponseEntity<>(blockCardResponse.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(blockCardResponse.getBody(), HttpStatus.OK);
	}

	@RequestMapping(value = "/v1/creditCards/cardServicing/{cardId}/unblock", method = RequestMethod.POST)
	@ApiOperation(value = "blockCard", nickname = "Temporarily unblock the credit card")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<?> unBlockCard(@PathVariable(value = "cardId", required = true) String cardNumber,
			@RequestParam(value = "crudIndicator") String crudIndicator,
			@RequestHeader(value = "client_id") String clientId, @RequestHeader(value = "Authorization") String auth,
			@RequestHeader(value = "uuid") String uuid, @RequestHeader(value = "Content-Type") String contentType,
			@RequestHeader(value = "Accept") String accept)
					throws ApplicationException, BadRequestException, ParseException {
		logger.info("unBlockCard()");

		ResponseEntity<?> blockCardResponse = null;

		if ((crudIndicator == null || crudIndicator.isEmpty()) || (clientId == null || clientId.isEmpty())
				|| (auth == null || auth.isEmpty()) || (uuid == null || uuid.isEmpty())
				|| (contentType == null || contentType.isEmpty()) || (accept == null || accept.isEmpty())) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.MISSING_PARAMETERS_REQUIRED.getValue());
		}

		if (!crudIndicator.matches("^[U]$")) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.INVALID_CRUD_INDICATOR.getValue());
		}

		String regex = "\\d+";
		if (!cardNumber.matches(regex)) {
			throw new BadRequestException(CardsMaintenanceErrorMessage.INVALID_CARD_NUMBER.getValue());
		}

		// Request headers
		reqData = new HashMap<String, String>();
		reqData.put("uuid", uuid);
		reqData.put("client_id", clientId);
		reqData.put("Authorization", auth);

		if (cardNumber != "" && cardNumber != null) {
			if (cardNumber.length() == 16) {
				try {
					blockCardResponse = cardsMaintenanceService.doBlockUnblockCard(crudIndicator, cardNumber, null,
							reqData);
				} catch (Exception e) {
					logger.error("Inside CardsMaintenanceController::::: ", e);
					return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				throw new BadRequestException(CardsMaintenanceErrorMessage.CARD_NUMBER_LENGHT_SHOULD_BE_16.getValue());
			}
		} else {
			throw new BadRequestException(CardsMaintenanceErrorMessage.MISSING_PARAMETERS_REQUIRED.getValue());
		}

		if (blockCardResponse.getBody() instanceof Errors) {
			return new ResponseEntity<>(blockCardResponse.getBody(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(blockCardResponse.getBody(), HttpStatus.OK);
	}

	private boolean validateDate(String date) throws ParseException, BadRequestException {
		boolean isValidDate = false;
		String datePattern = CardsMaintenanceConstant.DATE_PATTERN_FOR_VALIDATION.getValue();
		DateFormat df = new SimpleDateFormat(CardsMaintenanceConstant.DATE_IN_DDMMYYYY_FORMAT.getValue());
		if (date != null && date.matches(datePattern)) {
			Date blockTillDate = df.parse(date);
			String todaysDateInStringFormat = df.format(new Date());
			Date todaysDate = df.parse(todaysDateInStringFormat);
			if (blockTillDate.equals(todaysDate)) {
				throw new BadRequestException(
						CardsMaintenanceErrorMessage.DATE_MUST_BE_GREATER_THAN_TODAYS_DATE.getValue());
			}
			if (blockTillDate.after(todaysDate)) {
				isValidDate = true;
			} else {
				throw new BadRequestException(
						CardsMaintenanceErrorMessage.BACK_DATE_IS_NOT_ALLOWED_FOR_BLOCK_CARD.getValue());
			}
		}
		return isValidDate;
	}
}
