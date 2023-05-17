package kwu.raccooninfra.apis.chatting;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import kwu.raccooninfra.apis.chatting.ChattingApi;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.springframework.cloud.contract.wiremock.WireMockSpring.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = {ChattingMocks.class})
public class ChattingApisTest {

    private MockMvc mockMvc;
    @Autowired
    private ChattingApi chattingApi;
    static final int PORT = 1234;
    public static WireMockServer wireMockServer =
            new WireMockServer(options().port(PORT));

    @DynamicPropertySource
    public static void addUrlProperties(DynamicPropertyRegistry registry){
        registry.add("apis.chatting-room",()->"localhost:"+PORT);
    }

    @BeforeAll
    public static void beforeAll(){
        wireMockServer.start();
        configureFor("localhost",PORT);
    }

    @AfterAll
    public static void afterAll(){
        wireMockServer.stop();
    }

    @AfterEach
    public void afterEach(){
        wireMockServer.resetAll();
    }

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(chattingApi)
                .build();
    }

    @Test
    @DisplayName("ㅁㅁㅁ")
    public void aaa(){
        wireMockServer.stubFor(get(WireMock.urlPathMatching("/chat/users/[1-9]/rooms")))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("chat-room-response.json"))
        );
    }
}
