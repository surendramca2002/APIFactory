/*package com.citibanamex.api.cardsmaintenance.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.citibanamex.api.cardsmaintenance.CitibanamexApiCardsMaintenanceApplication;
import com.citibanamex.api.cardsmaintenance.exception.ApplicationException;
import com.citibanamex.api.cardsmaintenance.model.BlockCardResponse;
import com.citibanamex.api.cardsmaintenance.model.BlockCardStatus;
import com.citibanamex.api.cardsmaintenance.model.Ewoew2xloperationresponse;
import com.citibanamex.api.cardsmaintenance.model.Mli2xlOArea;
import com.citibanamex.api.cardsmaintenance.model.Mli2xlOHdrGrp;
import com.citibanamex.api.cardsmaintenance.service.impl.CardsMaintenanceServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CitibanamexApiCardsMaintenanceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardsMaintenanceServiceImplTest {

	@InjectMocks
	private CardsMaintenanceServiceImpl cardsMaintenanceServiceImpl;

	@Value("${environments.sit.block.card.service.uri}")
	private String blockCardServiceURI;

	@Value("${environments.sit.unblock.card.service.uri}")
	private String unBlockCardServiceURI;

	@Mock
	private RestTemplate restTemplate;

	@Test
	public void testBlockCard() throws ApplicationException {
		Map<String, String> data = null;
		data = new HashMap<String, String>();
		data.put("client_id", "ffe4d269-1c59-493b-94f4-fbca960bc0f0");
		data.put("Authorization", "Basic 4r43rf3f4gt4");
		data.put("uuid", "fewft34tggregreg");

		BlockCardResponse blockCardResponse = new BlockCardResponse();
		cardsMaintenanceServiceImpl.setTimeout(2000);
		*//** define the entity you want the exchange to return **//*
		ResponseEntity<BlockCardResponse> myEntity = new ResponseEntity<>(blockCardResponse, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Matchers.eq(blockCardServiceURI), Matchers.eq(HttpMethod.POST),
				Matchers.<HttpEntity<BlockCardResponse>>any(),
				Matchers.<ParameterizedTypeReference<BlockCardResponse>>any())).thenReturn(myEntity);

		ResponseEntity<?> serviceResponse = cardsMaintenanceServiceImpl.blockCard("12344565768798", 10, data);

		assertNotNull(serviceResponse);
		assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
		cardsMaintenanceServiceImpl.setTimeout(null);
	}

	@Test
	public void testUnBlockCard() throws ApplicationException {
		Map<String, String> data = null;
		data = new HashMap<String, String>();
		data.put("client_id", "ffe4d269-1c59-493b-94f4-fbca960bc0f0");
		data.put("Authorization", "Basic 4r43rf3f4gt4");
		data.put("uuid", "fewft34tggregreg");

		BlockCardResponse blockCardResponse = new BlockCardResponse();
		cardsMaintenanceServiceImpl.setTimeout(2000);
		*//** define the entity you want the exchange to return **//*
		ResponseEntity<BlockCardResponse> myEntity = new ResponseEntity<>(blockCardResponse, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Matchers.eq(unBlockCardServiceURI), Matchers.eq(HttpMethod.POST),
				Matchers.<HttpEntity<BlockCardResponse>>any(),
				Matchers.<ParameterizedTypeReference<BlockCardResponse>>any())).thenReturn(myEntity);

		ResponseEntity<?> serviceResponse = cardsMaintenanceServiceImpl.unBlockCard("12344565768798", data);

		assertNotNull(serviceResponse);
		assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
		cardsMaintenanceServiceImpl.setTimeout(null);
	}

	@Test
	public void testFallbackForBlockCard() {
		Map<String, String> data = null;
		data = new HashMap<String, String>();
		data.put("client_id", "ffe4d269-1c59-493b-94f4-fbca960bc0f0");
		data.put("Authorization", "Basic 4r43rf3f4gt4");
		data.put("uuid", "fewft34tggregreg");

		ResponseEntity<?> response = cardsMaintenanceServiceImpl.fallbackForBlockCard("12345", 11, data,
				new Throwable());
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void testFallbackForUnBlockCard() {
		Map<String, String> data = null;
		data = new HashMap<String, String>();
		data.put("client_id", "ffe4d269-1c59-493b-94f4-fbca960bc0f0");
		data.put("Authorization", "Basic 4r43rf3f4gt4");
		data.put("uuid", "fewft34tggregreg");

		ResponseEntity<?> response = cardsMaintenanceServiceImpl.fallbackForUnBlockCard("12345", data, new Throwable());
		assertNotNull(response);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void testGetBlockCardStatusForValidErrorCode() {
		Mli2xlOHdrGrp mli2xlOHdrGrp = new Mli2xlOHdrGrp();
		mli2xlOHdrGrp.setMli2xlOErrorCode("0000");
		Mli2xlOArea mli2xlOArea = new Mli2xlOArea();
		mli2xlOArea.setMli2xlOHdrGrp(mli2xlOHdrGrp);
		Ewoew2xloperationresponse ewoew2xloperationresponse = new Ewoew2xloperationresponse();
		ewoew2xloperationresponse.setMli2xlOArea(mli2xlOArea);
		BlockCardResponse blockCardResponse = new BlockCardResponse();
		blockCardResponse.setEwoew2xloperationresponse(ewoew2xloperationresponse);

		BlockCardStatus status = cardsMaintenanceServiceImpl.getBlockCardStatus(blockCardResponse);

		assertNotNull(status);
		assertEquals("BLOCK", status.getCardStatusType());
	}

	@Test
	public void testGetBlockCardStatusForInValidErrorCode() {
		Mli2xlOHdrGrp mli2xlOHdrGrp = new Mli2xlOHdrGrp();
		mli2xlOHdrGrp.setMli2xlOErrorCode("1234");
		Mli2xlOArea mli2xlOArea = new Mli2xlOArea();
		mli2xlOArea.setMli2xlOHdrGrp(mli2xlOHdrGrp);
		Ewoew2xloperationresponse ewoew2xloperationresponse = new Ewoew2xloperationresponse();
		ewoew2xloperationresponse.setMli2xlOArea(mli2xlOArea);
		BlockCardResponse blockCardResponse = new BlockCardResponse();
		blockCardResponse.setEwoew2xloperationresponse(ewoew2xloperationresponse);

		BlockCardStatus status = cardsMaintenanceServiceImpl.getBlockCardStatus(blockCardResponse);

		assertNotNull(status);
		assertEquals("UNBLOCK", status.getCardStatusType());
	}
}
*/