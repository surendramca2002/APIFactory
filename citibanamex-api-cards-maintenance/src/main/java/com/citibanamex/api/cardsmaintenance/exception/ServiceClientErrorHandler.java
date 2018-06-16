package com.citibanamex.api.cardsmaintenance.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class ServiceClientErrorHandler extends DefaultResponseErrorHandler {
	public static final Logger logger = LoggerFactory.getLogger(ServiceClientErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    	logger.info("Inside ServiceClientErrorHandler " + response.getHeaders());
    	logger.info("Inside ServiceClientErrorHandler " + response.getStatusCode());
    	logger.info("Inside ServiceClientErrorHandler " + response.getStatusText());
    	logger.info("Inside ServiceClientErrorHandler " + response.getBody());
}
}
