package kwu.raccoonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("kwu")
public class RaccoonApiApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name","application-api,application-domain,application-infra,application-event.yml");
        SpringApplication.run(RaccoonApiApplication.class, args);

    }

}
