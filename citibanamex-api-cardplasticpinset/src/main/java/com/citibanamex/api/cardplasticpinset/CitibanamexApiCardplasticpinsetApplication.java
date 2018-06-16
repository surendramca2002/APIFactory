package com.citibanamex.api.cardplasticpinset;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class CitibanamexApiCardplasticpinsetApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitibanamexApiCardplasticpinsetApplication.class, args);
	}
	@Bean
	public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.apiInfo(metaData())
	                .select()                
	                .apis(RequestHandlerSelectors.basePackage("com.citibanamex.api.cardplasticpinset.controller"))
	                .paths(regex("/v1/.*"))
	                .build();
	                
	   }
	

	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("Cardplastic").description("Card Plastic Microservice Documentation").build();
        return apiInfo;
    }
}
