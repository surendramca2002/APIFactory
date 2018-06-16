package com.citibanamex.api.cardsmaintenance.exception;

public class Error {

	private String type;
	private String code;
	private String details;
	private String location;
	private String moreInfo;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Override
	public String toString() {
		return String.format("Error [type=%s, code=%s, details=%s, location=%s, moreInfo=%s]", type, code, details,
				location, moreInfo);
	}
}
