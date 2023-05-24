package kwu.raccooninfra.apis.chatting;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import kwu.raccooninfra.config.WireMockConfig;
import kwu.raccooninfra.dto.ChatRoomDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.springframework.cloud.contract.wiremock.WireMockSpring.options;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
public class ChattingApisTest {
//    @Autowired
//    private ChattingApi chattingApi;
//    static final int PORT = 9561;
//
//    @DynamicPropertySource
//    public static void addUrlProperties(DynamicPropertyRegistry registry){
//        registry.add("apis.chatting-room",()->"localhost:"+PORT);
//    }
//
//
//
//    @Test
//    @DisplayName("ㅁㅁㅁ")
//    public void aaa(){
//        WireMock.stubFor(get(urlPathEqualTo("/chat/users/[1-9]/rooms"))
//                .willReturn(
//                        aResponse()
//                                .withStatus(HttpStatus.OK)
//                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                                .withBodyFile("chat-room-reponse.json")
//                )
//        );
//        chattingApi.getUserChatRooms(1L);
//
////        mockMvc.perform(get("/chat/users/[1-9]/rooms")).andExpect()
////
////        List<ChatRoomDto> userChatRooms = chattingApi.getUserChatRooms(1L);
//
//
//    }
}
