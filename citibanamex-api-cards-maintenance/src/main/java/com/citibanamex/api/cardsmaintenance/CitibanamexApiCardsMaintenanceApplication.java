package com.citibanamex.api.cardsmaintenance;

import static springfox.documentation.builders.PathSelectors.regex;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCircuitBreaker
@EnableSwagger2
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CitibanamexApiCardsMaintenanceApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CitibanamexApiCardsMaintenanceApplication.class, args);
		DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("CardsMaintenance").apiInfo(apiInfo()).select()
				.paths(regex("/cardsmaintenance.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring REST Cards API Sample with Swagger")
				.description("Spring REST Cards API Sample with Swagger")
				.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("2.0")
				.build();
	}

	/*
	 * Create a RestTemplate bean, using the RestTemplateBuilder provided by the
	 * auto-configuration.
	 */
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

		char[] password2 = "Citibank4".toCharArray();

		/*
		 * Create an SSLContext that uses client.jks as the client certificate
		 */
		SSLContext sslContext = SSLContextBuilder.create()
				.loadKeyMaterial(loadPfx("classpath:MX-SIT1.pfx", password2), password2)
				// .loadTrustMaterial(ResourceUtils.getFile("classpath:cacerts"),
				// password)
				.loadTrustMaterial(null, (certificate, authType) -> true).build();

		/*
		 * Create an HttpClient that uses the custom SSLContext
		 */
		HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				// .setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();

		/*
		 * Create a RestTemplate that uses a request factory that references our
		 * custom HttpClient
		 */
		return builder.requestFactory(new HttpComponentsClientHttpRequestFactory(client)).build();
	}

	private KeyStore loadPfx(String file, char[] password) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		File key = ResourceUtils.getFile(file);
		try (InputStream in = new FileInputStream(key)) {
			keyStore.load(in, password);
		}
		return keyStore;
	}

	@Bean
	DispatcherServlet dispatcherServlet() {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}

}
