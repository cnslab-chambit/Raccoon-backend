package kwu.raccoondomain.persistence.domain.user.enums;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Religion {
    CHRISTIAN("기독교"),
    BUDDHISM("불교"),
    ATHEIST("무교"),
    CATHOLIC("천주교"),
    ETC("기타");
    @JsonValue
    private final String kor;

    @Override
    public String toString() {
        return kor;
    }
}
