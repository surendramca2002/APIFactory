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
@JsonPropertyOrder({ "alpha2", "name", "numeric", "latitude", "longitude" })
public class Country {

	@JsonProperty("alpha2")
	private String alpha2;
	@JsonProperty("name")
	private String name;
	@JsonProperty("numeric")
	private String numeric;
	@JsonProperty("latitude")
	private Integer latitude;
	@JsonProperty("longitude")
	private Integer longitude;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("alpha2")
	public String getAlpha2() {
		return alpha2;
	}

	@JsonProperty("alpha2")
	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("numeric")
	public String getNumeric() {
		return numeric;
	}

	@JsonProperty("numeric")
	public void setNumeric(String numeric) {
		this.numeric = numeric;
	}

	@JsonProperty("latitude")
	public Integer getLatitude() {
		return latitude;
	}

	@JsonProperty("latitude")
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public Integer getLongitude() {
		return longitude;
	}

	@JsonProperty("longitude")
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
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
