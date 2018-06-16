/*
 * Copyright Banco Nacional de Mexico, S.A.
 * Integrante de Grupo Financiero Banamex.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.citibanamex.api.binvalidator.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citibanamex.api.binvalidator.exceptions.APIFactoryBaseException;
import com.citibanamex.api.binvalidator.exceptions.APIFactoryFallBackException;
import com.citibanamex.api.binvalidator.model.BINRequest;
import com.citibanamex.api.binvalidator.model.BINResponse;
import com.citibanamex.api.binvalidator.model.json.ExternalAPIResponse;
import com.citibanamex.api.binvalidator.service.IBINValidateService;
import com.citibanamex.api.binvalidator.util.BINValidatorConstant;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class BINValidateServiceImpl implements IBINValidateService {

	@Autowired
	private BINResponse binResponse;
	@Autowired
	private Environment env;
	
	private static final Logger log = LoggerFactory.getLogger(BINValidateServiceImpl.class);

	@Override
	@HystrixCommand(fallbackMethod = "fallbackValidateBIN", commandProperties={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")})
	public BINResponse validateBIN(BINRequest binRequest) throws APIFactoryBaseException {
		
		log.info("IN BINValidateServiceImpl..." + new Date().getTime());
		
		StringBuffer EXTERNAL_BIN_API_URI = new StringBuffer(env.getProperty(BINValidatorConstant.EXTERNAL_BIN_API_URI));
		log.error("****************EXTERNAL_BIN_API_URI****************" + EXTERNAL_BIN_API_URI);
		
		System.setProperty("proxyHost", env.getProperty(BINValidatorConstant.PROXY_HOST));
		System.setProperty("proxyPort", env.getProperty(BINValidatorConstant.PROXY_PORT));
		
		RestTemplate restTemplate = new RestTemplate();
		
		EXTERNAL_BIN_API_URI.append(binRequest.getBankIdentificationNumber());

		ExternalAPIResponse externalAPIResponse = restTemplate.getForObject(EXTERNAL_BIN_API_URI.toString().trim(),
				ExternalAPIResponse.class);

		if (null != externalAPIResponse) {
			binResponse.setCardType(externalAPIResponse.getType());
			binResponse.setCardNetworkType(externalAPIResponse.getScheme());
			binResponse.setBankIdentificationNumber(binRequest.getBankIdentificationNumber());

			if (null != externalAPIResponse.getBank()) {
				binResponse.setBankName(externalAPIResponse.getBank().getName());
			}

			if (null != externalAPIResponse.getCountry()) {
				binResponse.setCountryCode(externalAPIResponse.getCountry().getAlpha2());
			}
		}

		log.info("Exiting BINValidateServiceImpl..." + new Date().getTime());
		return binResponse;
	}
	
	public BINResponse fallbackValidateBIN(BINRequest binRequest) throws APIFactoryBaseException{
		throw new APIFactoryFallBackException();
	}
}