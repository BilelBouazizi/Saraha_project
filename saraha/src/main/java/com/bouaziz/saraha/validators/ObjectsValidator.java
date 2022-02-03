package com.bouaziz.saraha.validators;

import com.bouaziz.saraha.exceptions.ObjectValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator <T extends Serializable>{
//on accepte que les classe de type serializable
private final ValidatorFactory factory= Validation.buildDefaultValidatorFactory();//design pattern factory
private final Validator validator= factory.getValidator();//je répére mon factory
public void validate(T clazz){
    Set<ConstraintViolation<T>> violations=validator.validate(clazz);
    if(violations.size()>0){
        Set<String>errors=violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
   //buisness exception
    throw new ObjectValidationException(errors,clazz.getClass().getName());
        //todo Throw exception
    //l'api qui gére l'exception : on evite les object not found

    }
}
}
