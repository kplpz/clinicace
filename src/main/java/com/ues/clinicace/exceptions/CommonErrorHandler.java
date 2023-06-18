package com.ues.clinicace.exceptions;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
@Component
public class CommonErrorHandler {
    public static Map<String,Object> getFieldErrorResponse(BindingResult
                                                                   result){
        Map<String, Object> fielderror = new HashMap<>();
        List<FieldError>errors= result.getFieldErrors();
        for (FieldError error : errors) {
            fielderror.put(error.getField(),
                    error.getDefaultMessage());
        }return fielderror;
    }
    public static ResponseEntity<Object> fieldErrorResponse(String
                                                                    message,Object fieldError){
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("message", message);
        map.put("timeStamp", LocalDate.now());
        map.put("filedError", fieldError);
        return new
                ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
    }
}
