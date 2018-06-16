/*
 * Copyright Banco Nacional de Mexico, S.A.
 * Integrante de Grupo Financiero Banamex.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.citibanamex.api.binvalidator.exceptions;

/**
 * This is a Bad Request Exception class used to deal about bad requests types
 * 
 * @author AM241297
 *
 */
public class APIFactoryBadRequestException extends APIFactoryBaseException {
	/**
	 * serialVersion
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public APIFactoryBadRequestException() {
		super();
	}


	public APIFactoryBadRequestException(String type, String code, String details, String location, String moreInfo) {

		super(type, code, details, location, moreInfo);
	}

}
