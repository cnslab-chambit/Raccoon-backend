package kwu.raccoonapi.controller.verification;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoryValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Story {
    String message() default "Invalid story";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
    public int min() default 0;
    public int max() default 200;
    public boolean nullable() default false;
}
