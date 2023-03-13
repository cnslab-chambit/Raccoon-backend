package kwu.raccoonapi.exception;

import kwu.raccoonapi.dto.ApiResponse;
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
    public ApiResponse<?> errorHandler(RaccoonException e) {
        return ApiResponse.failure(e.getRetConsts(),e.getMessage());
    }

    @ExceptionHandler(RaccoonAuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> authErrorHandler(RaccoonAuthException e){
        return ApiResponse.failure(e.getRetConsts(),e.getMessage());
    }
}