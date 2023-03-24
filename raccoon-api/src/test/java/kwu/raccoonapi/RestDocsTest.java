package kwu.raccoonapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Disabled
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https",uriHost = "com.kwu.raccoonapi",uriPort = 443)
@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
public class RestDocsTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
    protected static RestDocumentationResultHandler restDocumentationResultHandler;

    protected static String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOltdLCJpYXQiOjE2Nzk2MzQ3ODAsImV4cCI6MTY4MzIzNDc4MH0.B1xbi3-lvKGYPf3hk73OTIUa7wtebzvvy8xI2tmYjW8";

    @BeforeAll
    static void restDocSetup(){restDocumentationResultHandler = MockMvcRestDocumentation.document("{class-name}/{method-name}");}


    public void setup(Object controller, RestDocumentationContextProvider provider){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(print())
                .alwaysDo(restDocumentationResultHandler)
                .addFilter(new CharacterEncodingFilter("UTF-8",true))
                .build();
    }

}
