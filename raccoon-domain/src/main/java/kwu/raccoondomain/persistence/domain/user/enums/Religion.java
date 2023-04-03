package kwu.raccoondomain.persistence.domain.user.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Religion {
    CHRISTIAN("기독교"),
    BUDDHISM("불교"),
    ATHEIST("무교"),
    CATHOLIC("천주교"),
    ETC("기타");
    private final String kor;

    @Override
    public String toString() {
        return kor;
    }
}
