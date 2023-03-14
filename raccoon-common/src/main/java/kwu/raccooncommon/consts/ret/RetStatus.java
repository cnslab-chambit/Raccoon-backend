package kwu.raccooncommon.consts.ret;

public enum RetStatus {

    Success(200),
    Auth(400),
    Validation(410),
    INFRA(420);
    private int retCode;

    private RetStatus(int retCode){
        this.retCode = retCode;
    }
}
