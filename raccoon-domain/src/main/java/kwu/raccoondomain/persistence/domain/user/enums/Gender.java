package kwu.raccoondomain.persistence.domain.user.enums;

public enum Gender {
    MAN,
    WOMAN;
    public static Gender parsing(String s){
        if(s == "male") return MAN;
        return WOMAN;
    }
}
