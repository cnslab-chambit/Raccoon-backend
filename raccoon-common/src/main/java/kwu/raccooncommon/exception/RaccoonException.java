package kwu.raccooncommon.exception;

import kwu.raccooncommon.consts.ret.RetConsts;
import lombok.Getter;

@Getter
public class RaccoonException extends RuntimeException{
    private RetConsts retConsts;

    public RaccoonException(RetConsts retConsts){
        super(retConsts.getDescription());
        this.retConsts = retConsts;
    }
}