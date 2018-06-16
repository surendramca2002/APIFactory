/*package com.citibanamex.api.cardsmaintenance.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.citibanamex.api.cardsmaintenance.CitibanamexApiCardsMaintenanceApplication;
import com.citibanamex.api.cardsmaintenance.exception.ApplicationException;
import com.citibanamex.api.cardsmaintenance.service.CardsMaintenanceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CitibanamexApiCardsMaintenanceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardMaintenanceControllerTest {

	@Autowired
	private CardsMaintenanceController cardsMaintenanceController;

	@Test
	public void testBlockCard() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("client_id", "eqd732edqwq");
		headers.set("Authorization", "Basic 4r43rf3f4gt4");
		headers.set("uuid", "fewft34tggregreg");

	}

	@Test(expected = ApplicationException.class)
	public void testBlockCardForInvalidCardLength() throws ApplicationException {	
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("client_id", "ffe4d269-1c59-493b-94f4-fbca960bc0f0");
		requestData.put("Authorization", "Basic 4r43rf3f4gt4");
		requestData.put("uuid", "fewft34tggregreg");
		CardsMaintenanceService cardsMaintenanceService = mock(CardsMaintenanceService.class);
		when(cardsMaintenanceService.blockCard("00000000000594113934873843438", 10, requestData)).thenReturn(new ResponseEntity<>(HttpStatus.OK));

		cardsMaintenanceController.blockCard("00000000000594113934873843438", 10, "ffe4d269-1c59-493b-94f4-fbca960bc0f0", "Basic 4r43rf3f4gt4", "wyudeewu", "application/json", "application/json");
		
	}

	@Test(expected = ApplicationException.class)
	public void testBlockCardForInvalidBlockDuration() throws ApplicationException {
		Map<String, String> requestData = new HashMap<String, String>();
		requestData.put("client_id", "ffe4d269-1c59-493b-94f4-fbca960bc0f0");
		requestData.put("Authorization", "Basic 4r43rf3f4gt4");
		requestData.put("uuid", "fewft34tggregreg");
		CardsMaintenanceService cardsMaintenanceService = mock(CardsMaintenanceService.class);	
		when(cardsMaintenanceService.blockCard("00000000000594113934873843438", 199, requestData)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		
		cardsMaintenanceController.blockCard("594113934873843438", 999, "ffe4d269-1c59-493b-94f4-fbca960bc0f0", "Basic 4r43rf3f4gt4", "wyudeewu", "application/json", "application/json");
	}

	@Test
	public void testUnBlockCard() {

	}
}
*/