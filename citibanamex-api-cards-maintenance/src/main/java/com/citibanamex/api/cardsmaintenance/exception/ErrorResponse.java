package com.citibanamex.api.cardsmaintenance.exception;

import java.util.List;

public class ErrorResponse {

	private List<Error> error;
    public void setErrors(List<Error> error) {
         this.error = error;
     }
     public List<Error> getError() {
         return error;
     }
}
