package kwu.raccooninfra.apis.chatting;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.http.MediaType;

public class ChattingMocks {
    public static void setupMockChattingResponse(WireMockServer mockCacheApi){
        mockCacheApi.stubFor(WireMock.get(WireMock.urlMatching("/offices/[1-9]"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("payload/get-office-response.json")
                )
        );
    }
}