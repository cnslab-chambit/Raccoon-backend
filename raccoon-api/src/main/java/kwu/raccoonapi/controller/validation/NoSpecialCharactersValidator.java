package kwu.raccoonapi.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpecialCharactersValidator implements ConstraintValidator<NoSpecialCharacters,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value==null){
            return true;
        }
        return !(value.matches(".*[!@#$%^&*()].*"));
    }
}
