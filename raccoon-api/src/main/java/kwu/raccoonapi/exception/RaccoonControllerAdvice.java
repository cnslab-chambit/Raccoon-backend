package kwu.raccoonapi.exception;

import kwu.raccooncommon.exception.RaccoonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class RaccoonControllerAdvice {

    @ExceptionHandler(RaccoonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String errorHandler(RaccoonException e) {
        return e.getRetConsts().getDescription();
    }

    @ExceptionHandler(RaccoonAuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String authErrorHandler(RaccoonAuthException e){
        return e.getRetConsts().getDescription();
    }
}