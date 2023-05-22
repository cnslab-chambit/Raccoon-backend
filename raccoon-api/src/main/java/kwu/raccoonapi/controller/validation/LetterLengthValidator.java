package kwu.raccoonapi.controller.validation;

import kwu.raccooncommon.utils.RaccoonStringUtils;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ch.qos.logback.classic.Logger;

public class LetterLengthValidator implements ConstraintValidator<LetterLength, String> {

    private int min;
    private int max;
    private boolean nullable;
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public void initialize(LetterLength storyValidator) {
        min=storyValidator.min();
        max=storyValidator.max();
        nullable=storyValidator.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return min == 0;
        }
        int length = (int) RaccoonStringUtils.getLetterLength(value);
        return length >= min && length <= max;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg)
                .addConstraintViolation();
    }
}
