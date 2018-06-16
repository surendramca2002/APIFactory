/**
 * 
 */
package com.citibanamex.api.cardsmaintenance.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.citibanamex.api.cardsmaintenance.exception.ApplicationException;

/**
 * The Interface CardsMaintenanceService.
 *
 * @author AS283859
 */
public interface CardsMaintenanceService {

	public ResponseEntity<?> doBlockUnblockCard(String crudIndicator, String cardNumber, String blockTillDate, Map<String, String> data) throws ApplicationException;

}
