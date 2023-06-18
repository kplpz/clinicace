package com.ues.clinicace.exceptions;

import org.springframework.validation.BindingResult;
public class InvalidDataException extends RuntimeException {
    private static final long serialVersionUID = -4164793146536667139L;
    private BindingResult result;
    public InvalidDataException(BindingResult result) {
        super();
        this.setResult(result);
    }
    public BindingResult getResult() {
        return result;
    }
    public void setResult(BindingResult result) {
        this.result = result;
    }
}
