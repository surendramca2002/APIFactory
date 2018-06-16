/**
 * 
 */
package com.citibanamex.api.cardsmaintenance.service.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citibanamex.api.cardsmaintenance.exception.ApplicationException;
import com.citibanamex.api.cardsmaintenance.exception.Error;
import com.citibanamex.api.cardsmaintenance.exception.Errors;
import com.citibanamex.api.cardsmaintenance.exception.ServiceClientErrorHandler;
import com.citibanamex.api.cardsmaintenance.model.CardLockUnlockOvCdReqRec;
import com.citibanamex.api.cardsmaintenance.model.CardRequest;
import com.citibanamex.api.cardsmaintenance.model.CardResponse;
import com.citibanamex.api.cardsmaintenance.model.CardStatus;
import com.citibanamex.api.cardsmaintenance.model.CardsMaintenanceConstant;
import com.citibanamex.api.cardsmaintenance.model.EWO2PA04Operation;
import com.citibanamex.api.cardsmaintenance.service.CardsMaintenanceService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author AS283859
 *
 */
@Service
public class CardsMaintenanceServiceImpl implements CardsMaintenanceService {

	public static final Logger logger = LoggerFactory.getLogger(CardsMaintenanceServiceImpl.class);
	private HttpHeaders headers;
	private CardStatus cardStatus;

	@Autowired
	private RestTemplate rt;

	@Value("${block.unblock.card.service.uri}")
	private String cardServiceURI;

	@Value("${circuit.breaker.external.service.timeout}")
	private Integer timeout;

	@HystrixCommand(fallbackMethod = "fallbackForDoBlockUnblockCard")
	public ResponseEntity<?> doBlockUnblockCard(String crudIndicator, String cardNumber, String blockTillDate,
			Map<String, String> data) throws ApplicationException {
		HystrixCommandProperties.Setter().getCircuitBreakerForceClosed();
		HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeout);
		disableSslVerification();
		CardLockUnlockOvCdReqRec cardLockUnlockOvCdReqRec = null;
		EWO2PA04Operation eWO2PA04Operation = null;
		CardRequest request = null;
		CardResponse cardResponse = null;

		headers = new HttpHeaders();
		headers.set("Authorization", data.get("Authorization"));
		headers.set("client_id", data.get("client_Id"));
		headers.set("uuid", data.get("uuid"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Request Payload
		cardLockUnlockOvCdReqRec = new CardLockUnlockOvCdReqRec();
		cardLockUnlockOvCdReqRec.setAfewsMessageID(CardsMaintenanceConstant.AFEWS_MESSAGE_ID.getValue());
		cardLockUnlockOvCdReqRec.setSourceSystem(CardsMaintenanceConstant.SOURCE_SYSTEM.getValue());
		cardLockUnlockOvCdReqRec.setcICControlID(CardsMaintenanceConstant.CIC_CONTROL_ID.getValue());
		cardLockUnlockOvCdReqRec.setCountryCode(CardsMaintenanceConstant.COUNTRY_CODE.getValue());
		cardLockUnlockOvCdReqRec.setOverrideCd(CardsMaintenanceConstant.OVERRIDE_CD.getValue());
		cardLockUnlockOvCdReqRec.setApplyTillDateDDMMYYYY(blockTillDate);
		cardLockUnlockOvCdReqRec.setCardNumber(CardsMaintenanceConstant.CARDS_PRECEDING_ZEROS.getValue() + cardNumber);
		cardLockUnlockOvCdReqRec.setcRUDIndicator(crudIndicator);
		String DateInDDMMYYYYFormat = new SimpleDateFormat(CardsMaintenanceConstant.DATE_IN_DDMMYYYY_FORMAT.getValue())
				.format(new Date());
		String timeInHHmmssFormat = new SimpleDateFormat(CardsMaintenanceConstant.TIME_IN_HHMMSS_FORMAT.getValue())
				.format(new Date());
		//String timeInMSSSSSSSFormat = new SimpleDateFormat(CardsMaintenanceConstant.TIME_IN_MSSSSSSS_FORMAT.getValue())
		//		.format(new Date());
		cardLockUnlockOvCdReqRec.setRequestDateDDMMYYYY(DateInDDMMYYYYFormat);
		cardLockUnlockOvCdReqRec.setRequestTimeHHMMSS(timeInHHmmssFormat);
		cardLockUnlockOvCdReqRec.setRequestTimeMSSSSSSS("000000");

		eWO2PA04Operation = new EWO2PA04Operation();
		eWO2PA04Operation.setCardLockUnlockOvCdReqRec(cardLockUnlockOvCdReqRec);
		request = new CardRequest();
		request.seteWO2PA04Operation(eWO2PA04Operation);
		logger.info("Payload >>>>" + request);

		HttpEntity<?> entity = new HttpEntity<>(request, headers);

		ResponseEntity<CardResponse> serviceResp = null;
		try {
			rt.setErrorHandler(new ServiceClientErrorHandler());
			serviceResp = rt.exchange(cardServiceURI, HttpMethod.POST, entity, CardResponse.class);
			logger.info("CardServiceURI Response >>>>>" + serviceResp.getBody());

			if (serviceResp != null && (cardResponse = serviceResp.getBody()) != null) {
				cardStatus = getCardStatus(cardResponse, crudIndicator);
			}

		} catch (Exception e) {
			logger.error("Inside CardsMaintenanceServiceImpl::::: ", e.getMessage(), e);
			throw new ApplicationException(e.getMessage(), e);
		}

		return new ResponseEntity<>(cardStatus, HttpStatus.OK);
	}

	public ResponseEntity<?> fallbackForDoBlockUnblockCard(String crudIndicator, String cardNumber,
			String blockTillDate, Map<String, String> data, Throwable t) {
		logger.info("Inside fallbackForDoBlockUnblockCard()");
		logger.error("Cause for fallback::::: " + t.getMessage());
		Errors errors = null;
		Error error = null;
		ArrayList<Error> errorList = null;
		errors = new Errors();
		error = new Error();
		error.setType("Error");
		error.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		error.setDetails(HttpStatus.INTERNAL_SERVER_ERROR.name());
		error.setLocation("");
		error.setMoreInfo(t.getMessage());
		errorList = new ArrayList<Error>();
		errorList.add(error);
		errors.setErrors(errorList);
		
		return new ResponseEntity<Errors>(errors, null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public CardStatus getCardStatus(CardResponse cardResponse, String crudIndicator) {
		cardStatus = new CardStatus();
		if (crudIndicator.equals(CardsMaintenanceConstant.CRUD_INDICATOR_LOCKED.getValue())) {
			if (cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReturnCode()
					.equals(CardsMaintenanceConstant.BACKEND_SUCCESSFULL_OPERATION_RETURN_CODE.getValue()) && 
					cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReasonCode().isEmpty()) {
				if (cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getLocked()
						.equals(CardsMaintenanceConstant.BACKEND_LOCKED_INDICATOR.getValue())) {
					cardStatus.setCardStatus(CardsMaintenanceConstant.BLOCKED_CARD_STATUS.getValue());
				}
			} else if (cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReturnCode()
					.equals(CardsMaintenanceConstant.BACKEND_UNSUCCESSFULL_OPERATION_RETURN_CODE.getValue())
					&& cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReasonCode()
							.equals(CardsMaintenanceConstant.BACKEND_UNSUCCESSFULL_OPERATION_REASON_CODE_FOR_LOCK
									.getValue())
					&& cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getLocked().isEmpty()) {
				cardStatus.setCardStatus(CardsMaintenanceConstant.ALREADY_BLOCKED_CARD_STATUS.getValue());
			}
		} else if (crudIndicator.equals(CardsMaintenanceConstant.CRUD_INDICATOR_UNLOCKED.getValue())) {
			if (cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReturnCode()
					.equals(CardsMaintenanceConstant.BACKEND_SUCCESSFULL_OPERATION_RETURN_CODE.getValue())&& 
					cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReasonCode().isEmpty()) {
				if (cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getLocked()
						.equals(CardsMaintenanceConstant.BACKEND_UNLOCKED_INDICATOR.getValue())) {
					cardStatus.setCardStatus(CardsMaintenanceConstant.UNBLOCKED_CARD_STATUS.getValue());
				}
			} else if (cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReturnCode()
					.equals(CardsMaintenanceConstant.BACKEND_UNSUCCESSFULL_OPERATION_RETURN_CODE.getValue())
					&& cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getReasonCode()
							.equals(CardsMaintenanceConstant.BACKEND_UNSUCCESSFULL_OPERATION_REASON_CODE_FOR_UNLOCK
									.getValue())
					&& cardResponse.getEWO2PA04OperationResponse().getCardLockUnlockOvCdResRec().getLocked().isEmpty()) {
				cardStatus.setCardStatus(CardsMaintenanceConstant.ALREADY_UNBLOCKED_CARD_STATUS.getValue());
			}
		}

		return cardStatus;

	}

	private static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

}
