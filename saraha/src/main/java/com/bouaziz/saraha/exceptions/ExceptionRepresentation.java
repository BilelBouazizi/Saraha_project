package com.bouaziz.saraha.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@JsonInclude(Include.NON_EMPTY)//s'il y a une variable qui n'est pas vide retourne la;retoune un message d'erreur
@Data
@Builder
public class ExceptionRepresentation {
    //les infos à exposer
    private String errorMessage;

    private String errorSource;

    private Set<String> validationErrors;
}
