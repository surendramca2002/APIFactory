package com.citibanamex.api.binvalidator.exceptions;

public class APIFactoryBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String code;
	private String details;
	private String location;
	private String moreInfo;

	public APIFactoryBaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIFactoryBaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public APIFactoryBaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public APIFactoryBaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public APIFactoryBaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


	public APIFactoryBaseException(String type, String code, String details, String location, String moreInfo) {
		this.type = type;
		this.code = code;
		this.details = details;
		this.location = location;
		this.moreInfo = moreInfo;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the moreInfo
	 */
	public String getMoreInfo() {
		return moreInfo;
	}

	/**
	 * @param moreInfo the moreInfo to set
	 */
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
