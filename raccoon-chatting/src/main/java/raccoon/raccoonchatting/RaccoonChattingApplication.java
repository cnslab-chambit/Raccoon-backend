package raccoon.raccoonchatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaccoonChattingApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name","application-chatting.yml");
        SpringApplication.run(RaccoonChattingApplication.class, args);
    }
}
