package com.citibanamex.api.binvalidator.model.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "scheme", "number", "type", "brand", "prepaid", "bank", "country" })
public class ExternalAPIResponse {

	@JsonProperty("scheme")
	private String scheme;
	@JsonProperty("number")
	private Number number;
	@JsonProperty("type")
	private String type;
	@JsonProperty("brand")
	private String brand;
	@JsonProperty("prepaid")
	private Boolean prepaid;
	@JsonProperty("bank")
	private Bank bank;
	@JsonProperty("country")
	private Country country;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("scheme")
	public String getScheme() {
		return scheme;
	}

	@JsonProperty("scheme")
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	@JsonProperty("number")
	public Number getNumber() {
		return number;
	}

	@JsonProperty("number")
	public void setNumber(Number number) {
		this.number = number;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("brand")
	public String getBrand() {
		return brand;
	}

	@JsonProperty("brand")
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@JsonProperty("prepaid")
	public Boolean getPrepaid() {
		return prepaid;
	}

	@JsonProperty("prepaid")
	public void setPrepaid(Boolean prepaid) {
		this.prepaid = prepaid;
	}

	@JsonProperty("bank")
	public Bank getBank() {
		return bank;
	}

	@JsonProperty("bank")
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@JsonProperty("country")
	public Country getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(Country country) {
		this.country = country;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}