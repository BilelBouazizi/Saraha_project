package com.bouaziz.saraha.exceptions;

import lombok.Getter;

import java.util.Set;

public class ObjectValidationException extends RuntimeException {
//Runtime : pas d'erreur de compilation , on aura l'erreur dans l'execution
   @Getter
    private final Set<String> violations;
   @Getter
    private final String validationSource;//mél user , wala message wala mel notification

    public ObjectValidationException(Set<String> violations,String validationSource){
        this.violations=violations;
        this.validationSource=validationSource;
    }
}
/*2eme méthode
@RequiredArgsContructor//lombok
* public class ObjectValidationException extends RuntimeException {
//Runtime : pas d'erreur de compilation , on aura l'erreur dan l'execution
   @Getter
    private final Set<String> violations;
   @Getter
    private final String validationSource;//mél user , wala message wala mel notification

}
* */
