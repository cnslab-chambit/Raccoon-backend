package kwu.raccooninfra.apis.chatting;

import kwu.raccooninfra.apis.chatting.ChattingApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class ChattingApisTest {
    @Autowired
    private ChattingApi chattingApi;

}
