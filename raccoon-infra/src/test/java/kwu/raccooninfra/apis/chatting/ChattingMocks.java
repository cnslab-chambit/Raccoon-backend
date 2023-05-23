package kwu.raccooninfra.apis.chatting;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;

public class ChattingMocks {
    public static void setupMockChattingResponse(WireMockServer mockCacheApi){
        mockCacheApi.stubFor(get(WireMock.urlMatching("/chat/users/[1-9]/rooms"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("chat-room-response.json")
                )
        );
    }
}
