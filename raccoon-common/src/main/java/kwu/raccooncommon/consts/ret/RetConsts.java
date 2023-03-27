package kwu.raccooncommon.consts.ret;

import lombok.Getter;

@Getter
public enum RetConsts {

    NRM000(RetStatus.Success,200,"성공"),
    ERR401(RetStatus.Auth,401,"토큰이 휴효하지 않음"),
    ERR403(RetStatus.Auth,403,"토큰누락"),

    ERR500(RetStatus.INFRA,500,"파일 업로드 에러"),
    ERR501(RetStatus.INFRA,501,"파일 url 이상함"),
    ERR600(RetStatus.BUSINESS,600,"해당 유저가 없어요"),
    ERR601(RetStatus.BUSINESS,601,"회원 정보가 일치하지 않습니다."),
    ERR602(RetStatus.BUSINESS,602,"해당 스토리가 존재하지 않습니다.");
    private RetStatus retStatus;
    private int code;
    private String description;

    private RetConsts(RetStatus retStatus,int code,String description){
        this.retStatus = retStatus;
        this.code = code;
        this.description = description;
    }
}
