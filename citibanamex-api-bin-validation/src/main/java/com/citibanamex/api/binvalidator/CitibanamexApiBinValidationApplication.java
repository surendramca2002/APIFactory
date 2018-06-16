package com.citibanamex.api.binvalidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.citibanamex.api")
@EnableSwagger2
@EnableCircuitBreaker
public class CitibanamexApiBinValidationApplication {

	private static final Logger log = LoggerFactory.getLogger(CitibanamexApiBinValidationApplication.class);

	public static void main(String[] args) {
		log.info("In main method of CitibanamexApiBinValidationApplication...");
		SpringApplication.run(CitibanamexApiBinValidationApplication.class, args);
	}
	
	
	/**
	 * This method is for swagger documentation
	 * 
	 * @return Docket
	 */
	/*@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Card Plastic Microservice").apiInfo(apiInfo()).select()
				.paths(regex("/api/v1/cardPlastic/.*")).build();
	}
	
	*//**
	 * This method is for swagger documentation
	 * 
	 * @return ApiInfo
	 *//*
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Card Plastic Microservice").description("APIs for Card Plastic Microservice")
				.version("1.0.0")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open").contact("AM241297")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("2.0")
				.build();
	}*/

	/**
	 * To handle 404 Not Found Exception
	 * 
	 * @return
	 */
	@Bean
	DispatcherServlet dispatcherServlet() {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}
}