/*
 * Copyright Banco Nacional de Mexico, S.A.
 * Integrante de Grupo Financiero Banamex.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.citibanamex.api.binvalidator.service;

import com.citibanamex.api.binvalidator.exceptions.APIFactoryBaseException;
import com.citibanamex.api.binvalidator.model.BINRequest;
import com.citibanamex.api.binvalidator.model.BINResponse;

/**
 * This is a service class to fetch list of nearby banamex ATMs from google
 * places api.
 * 
 * @author AM241297
 *
 */
public interface IBINValidateService {
	/**
	 * This method is used to validate the Bank Identification Number.
	 * 
	 * @param cardNumber
	 * @throws APIFactoryBaseException
	 */

	BINResponse validateBIN(BINRequest binRequest) throws APIFactoryBaseException;
}
