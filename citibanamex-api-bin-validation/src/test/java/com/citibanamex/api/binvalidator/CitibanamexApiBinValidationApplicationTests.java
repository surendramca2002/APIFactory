package com.citibanamex.api.binvalidator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.citibanamex.api.binvalidator.model.BINResponse;
import com.citibanamex.api.binvalidator.model.base.BaseErrorResponse;
import com.citibanamex.api.binvalidator.util.BINValidatorConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CitibanamexApiBinValidationApplicationTests.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ComponentScan("com.citibanamex.api")
public class CitibanamexApiBinValidationApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private BaseErrorResponse baseErrorResponse;
	@Autowired
	private Environment env;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	/*@Test
	public void test_Card_Number_With_Alpha_Numeric() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE)
				+env.getProperty(BINValidatorConstant.MESSAGE_CARD_NUMBER_WITH_ALPHA_NUMERIC)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));

	}*/

	/*@Test
	public void test_Card_Number_With_Length_Less_Than_Eight() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + "4208652" + env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));

	}*/

	/*@Test
	public void test_Card_Number_With_Length_Greater_Than_Sixteen() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + "420865223" + env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));

	}*/

	@Test
	public void test_Valid_Card_Number() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, BINResponse> cardPlasticResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<HashMap<String, BINResponse>>() {
				});
		assertEquals(cardPlasticResponse.get(env.getProperty(BINValidatorConstant.MESSAGE_CARD)).getCardType(), env.getProperty(BINValidatorConstant.MESSAGE_CARD_TYPE_DEBIT));
	}

	@Test
	public void test_Valid_Card_Number_Not_Found() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER_NOT_FOUND)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_404)));
	}

	@Test
	public void test_Valid_Card_Number_With_Missing_Authorization_Header() throws Exception {
		MvcResult result = this.mockMvc
				.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO)).header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT))
						.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
						.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		assertEquals(result.getResponse().getErrorMessage(),
				env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZTION_MISSING));
	}

	@Test
	public void test_Valid_Card_Number_With_Missing_UUID_Header() throws Exception {
		MvcResult result = this.mockMvc.perform(
				get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER) + env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO)).header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT))
				.header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))).andReturn();
		assertEquals(result.getResponse().getErrorMessage(),
				env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID_MISSING));
	}
	
	@Test
	public void test_Authorization_Header_AS_Null() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", "")
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_401)));
	}
	
	@Test
	public void test_UUID_Header_AS_Null() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", "").header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));
	}
	
	@Test
	public void test_Accept_Language_Header_AS_Null() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", "")
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));
	}
	
	@Test
	public void test_Accept_Header_AS_Null() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", "").header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_CLIENT_ID))).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));
	}
	
	@Test
	public void test_Client_Id_Header_AS_Null() throws Exception {
		MvcResult result = this.mockMvc.perform(get(env.getProperty(BINValidatorConstant.MESSAGE_URI_ONE) + env.getProperty(BINValidatorConstant.MESSAGE_VALID_CARD_NUMBER)+ env.getProperty(BINValidatorConstant.MESSAGE_URI_TWO))
				.header("Accept", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT)).header("Authorization", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_AUTHORIZATION))
				.header("uuid", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_UUID)).header("Accept-Language", env.getProperty(BINValidatorConstant.MESSAGE_HEADER_ACCEPT_LANGUAGE))
				.header("client_id", "")).andReturn();
		ObjectMapper objectMapper = new ObjectMapper();
		baseErrorResponse = objectMapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<BaseErrorResponse>() {
				});
		assertThat(String.valueOf(baseErrorResponse.getCode()), equalTo(env.getProperty(BINValidatorConstant.MESSAGE_HTTP_STATUS_400)));
	}
	
}
