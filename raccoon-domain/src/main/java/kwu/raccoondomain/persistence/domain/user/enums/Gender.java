package kwu.raccoondomain.persistence.domain.user.enums;

public enum Gender {
    MAN,
    WOMAN;
    public static Gender from(String s){
        if(s.equals("male")) return MAN;
        return WOMAN;
    }
}
