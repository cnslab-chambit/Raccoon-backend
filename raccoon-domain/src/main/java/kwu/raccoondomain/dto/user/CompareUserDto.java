package kwu.raccoondomain.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompareUserDto {
    private Long me;
    private Long opp;
    public static CompareUserDto of(Long me, Long opp){
        CompareUserDto dto = new CompareUserDto();
        dto.me = me;
        dto.opp = opp;
        return dto;
    }
}
