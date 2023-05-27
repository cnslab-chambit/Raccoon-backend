package kwu.raccoonapi.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoSpecialCharactersValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpecialCharacters {
    String message() default "Invalid nickname: special characters not allowed";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
