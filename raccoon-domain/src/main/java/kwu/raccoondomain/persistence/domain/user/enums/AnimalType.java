package kwu.raccoondomain.persistence.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AnimalType {
    //공통
    DOG("강아지"),
    RABBIT("토끼"),
    CAT("고양이"),

    //여자
    DEER("사슴"),
    FOX("여우"),

    //남자
    DINOSAUR("공룡"),
    BEAR("곰");
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
