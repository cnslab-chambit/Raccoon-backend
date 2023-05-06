package kwu.raccoondomain.unit.configs;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class DataSourceSelector {

    public static final String WRITE = "write";
    public static final String READ = "read";

    private String selected = WRITE;

    public void toWrite() {
        this.selected = WRITE;
//        TUser.toWrite();
    }

    public void toRead() {
        this.selected = READ;
//        TUser.toRead();
    }

    public String getSelected() {
        return selected;
    }
}
