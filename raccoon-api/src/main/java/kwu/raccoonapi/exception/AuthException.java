package kwu.raccoonapi.exception;

import kwu.raccooncommon.consts.ret.RetConsts;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthException extends AuthenticationException {
    public AuthException(RetConsts retConsts){
        super(retConsts.getDescription());
        this.retConsts = retConsts;
    }
    private RetConsts retConsts;
}
