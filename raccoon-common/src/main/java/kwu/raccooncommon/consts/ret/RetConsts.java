package kwu.raccooncommon.consts.ret;

import lombok.Getter;

@Getter
public enum RetConsts {

    NRM000(RetStatus.Success,200,"성공"),
    ERR401(RetStatus.Auth,401,"토큰이 휴효하지 않음"),
    ERR403(RetStatus.Auth,403,"토큰누락");

    private RetStatus retStatus;
    private int code;
    private String description;

    private RetConsts(RetStatus retStatus,int code,String description){
        this.retStatus = retStatus;
        this.code = code;
        this.description = description;
    }
}
