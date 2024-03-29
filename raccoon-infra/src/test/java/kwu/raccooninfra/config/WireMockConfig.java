package kwu.raccooninfra.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockConfig {
    @Autowired
    private WireMockServer wireMockServer;

    @Bean(initMethod = "start",destroyMethod = "stop")
    public WireMockServer mockService(){
        return new WireMockServer(9561);
    }
}
