package net.pelozo.parcial.leonardo.velozo.exception;


import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request){
        List<String> errors = new ArrayList<>();
        for(ConstraintViolation violation : ex.getConstraintViolations()){
            errors.add(violation.getMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpstatus());
    }

    @ExceptionHandler({AlreadyInList.class})
    public ResponseEntity<Object> handleConstraintViolation(AlreadyInList ex, WebRequest request){
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpstatus());
    }

    @ExceptionHandler({IOException.class, InterruptedException.class, ParseException.class})
    public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request){
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.FAILED_DEPENDENCY, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpstatus());
    }

}
