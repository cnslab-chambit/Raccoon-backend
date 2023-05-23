package kwu.raccoondomain.persistence.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AnimalType {
    //공통
    DOG("강아지상"),
    RABBIT("토끼상"),
    CAT("고양이상"),

    //여자
    DEER("사슴상"),
    FOX("여우상"),

    //남자
    DINOSAUR("공룡상"),
    BEAR("곰상");
    @JsonValue
    private final String kor;
    @Override
    public String toString() {
        return kor;
    }

    public static AnimalType fromString(String s){
        return Arrays.stream(AnimalType.values())
                .filter(t -> t.getKor().equals(s))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(s  + " is illegal argument."));
    }

}
