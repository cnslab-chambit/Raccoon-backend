package kwu.raccooncommon.consts.ret;

import lombok.Getter;

@Getter
public enum RetConsts {

    NRM000(RetStatus.Success,200,"성공");

    private RetStatus retStatus;
    private int code;
    private String description;

    private RetConsts(RetStatus retStatus,int code,String description){
        this.retStatus = retStatus;
        this.code = code;
        this.description = description;
    }
}
