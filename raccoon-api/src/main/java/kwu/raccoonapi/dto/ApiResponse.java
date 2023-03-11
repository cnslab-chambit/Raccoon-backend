package kwu.raccoonapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccooncommon.consts.ret.RetConsts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ApiResponse<T> {
    @JsonIgnore
    private final RetConsts retConsts;
    private final String message;
    private final int code;
    private final T data;

    public static <T> ApiResponse<T> ok(){
        return new ApiResponse<>(
                RetConsts.NRM000,
                RetConsts.NRM000.getDescription(),
                RetConsts.NRM000.getCode(),
                null
        );
    }

}
