package kwu.raccoonapi.controller.verification;

import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ch.qos.logback.classic.Logger;

public class StoryValidator implements ConstraintValidator<Story, String> {

    private int min;
    private int max;
    private boolean nullable;
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Override
    public void initialize(Story storyValidator) {
        min=storyValidator.min();
        max=storyValidator.max();
        nullable=storyValidator.nullable();
    }

    @Override
    public boolean isValid(String story, ConstraintValidatorContext context) {
        if (StringUtils.hasText(story)) {
            if (story.length() < min || story.length() > max) {
                addConstraintViolation(context,
                        String.format("%d~%d자 이내로 입력해주세요.", min, max));
                return false;
            }
        }
        else if(!nullable){
            addConstraintViolation(
                    context,
                    "글을 작성해주세요."
            );
            return false;
        }
        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg)
                .addConstraintViolation();
    }
}
