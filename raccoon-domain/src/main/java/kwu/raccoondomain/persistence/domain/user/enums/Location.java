package kwu.raccoondomain.persistence.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
@Getter
@RequiredArgsConstructor
public enum Location {
    YONGSAN("용산구"),
    JONGNO("종로구"),
    JUNG("중구"),
    SEONGDONG("성동구"),
    GWANGJIN("광진구"),
    DONGDAEMUN("동대문구"),
    JUNGNANG("중랑구"),
    SEONGBUK("성북구"),
    GANGBUK("강북구"),
    DOBONG("도봉구"),
    NOWON("노원구"),
    ENUPYEONG("은평구"),
    SEODAEMUN("서대문구"),
    MAPO("마포구"),
    YANGCHEON("양천구"),
    GANGSEO("강서구"),
    GURO("구로구"),
    GEUMCHEON("금천구"),
    YEONGDEUNGPO("영등포구"),
    DONGJAK("동작구"),
    GWANAK("관악구"),
    SEOCHO("서초구"),
    GANGNAM("강남구"),
    SONGPA("송파구"),
    GANDONG("강동구");
    private final String state;
    @Override
    public String toString() {
        return state;
    }

    @JsonCreator
    public static Location of(String state){
        return Arrays.stream(Location.values())
                .filter(t -> t.state.equals(state))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("asd"));
    }

}
