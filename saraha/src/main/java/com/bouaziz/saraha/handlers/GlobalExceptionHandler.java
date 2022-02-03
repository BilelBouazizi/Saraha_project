package com.bouaziz.saraha.handlers;

import com.bouaziz.saraha.exceptions.ExceptionRepresentation;
import com.bouaziz.saraha.exceptions.ObjectValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

//gestionnaire globale d'exception
@RestControllerAdvice//hérite de component
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception){
    ExceptionRepresentation exceptionRepresentation =ExceptionRepresentation.builder()

            .errorMessage("object not found exception")//optionnel , on peut l'enlever
            .errorSource(exception.getValidationSource())
            .validationErrors(exception.getViolations())
            .build();
        return ResponseEntity.badRequest().body(exceptionRepresentation);//status 400
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        ExceptionRepresentation exceptionRepresentation =ExceptionRepresentation.builder()

            .errorMessage("entity not found exception")//optionnel , on peut l'enlever
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionRepresentation);//status 400

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation>handleException(BadCredentialsException exception){
        ExceptionRepresentation exceptionRepresentation = ExceptionRepresentation.builder()
                .errorMessage("Login and / or password is incorrect")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionRepresentation);
    }
}
