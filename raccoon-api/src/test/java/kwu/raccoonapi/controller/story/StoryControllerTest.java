package kwu.raccoonapi.controller.story;

import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kwu.raccoonapi.controller.api.story.StoryController;
import kwu.raccoonapi.document.utils.ApiDocumentationTest;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.facade.story.StoryFacadeService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static kwu.raccooncommon.consts.CommonConsts.BEARER;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StoryControllerTest extends ApiDocumentationTest {

    @InjectMocks
    StoryController storyController;

    @Mock
    StoryFacadeService storyFacadeService;

    @BeforeEach
    void setup(RestDocumentationContextProvider provider){
        super.setup(storyController,provider);
    }

    @Test
    @DisplayName("스토리 생성")
    void 스토리_생성() throws Exception{
        String contents = "스토리의 내용입니다.";
        MockMultipartFile storyImage = new MockMultipartFile("storyImage","image.jpg".getBytes());

        JSONObject request = new JSONObject();

        request.put("contents",contents)
                .put("storyImage",storyImage);

        StoryCreateResponse response = StoryCreateResponse.of(1L);

        when(storyFacadeService.create(any()))
                .thenReturn(response);

        ResultActions resultActions = mockMvc.perform(post("/story")
                        .header(AUTHORIZATION,BEARER+" "+JWT)
                        .contentType(MULTIPART_FORM_DATA)
                        .content(request.toString())
            ).andExpect(status().isOk());

        resultActions.andDo(restDocs.document(resource(
                ResourceSnippetParameters.builder()
                        .description("스토리 생성 API")
                        .summary("스토리 생성 API")
                        .requestFields(
                                fieldWithPath("contents").description("스토리 내용"),
                                fieldWithPath("storyImage").description("스토리 이미지")
                        )
                        .requestHeaders(
                                ResourceDocumentation.headerWithName(AUTHORIZATION).description("ACCESS TOKEN")
                        )
                        .responseFields(
                                fieldWithPath("message").description("메시지"),
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("data.storyId").description("스토리 id"))
                        .tag("Story")
                        .build()
        )));
    }
    @Test
    @DisplayName("스토리 업데이트")
    void 스토리_업데이트() throws Exception{
        Long storyId = 1L;
        String contents = "스토리의 내용이에요";
        MockMultipartFile storyImage = new MockMultipartFile("storyImage","image.jpg".getBytes());

        JSONObject request = new JSONObject();

        request.put("storyId",storyId)
                .put("contents",contents)
                .put("storyImage",storyImage);

        StoryUpdateResponse response = StoryUpdateResponse.of(1L);

        when(storyFacadeService.updateStory(any()))
                .thenReturn(response);

        ResultActions resultActions = mockMvc.perform(patch("/story")
                        .header(AUTHORIZATION,BEARER+" "+JWT)
                        .contentType(MULTIPART_FORM_DATA)
                        .content(request.toString())
                ).andExpect(status().isOk());

        resultActions.andDo(restDocs.document(resource(
                ResourceSnippetParameters.builder()
                        .description("스토리 변경 API")
                        .summary("스토리 변경 API")
                        .requestFields(
                                fieldWithPath("storyId").description("스토리 ID"),
                                fieldWithPath("contents").description("내용"),
                                fieldWithPath("storyImage").description("스토리 이미지")
                        )
                        .requestHeaders(
                                ResourceDocumentation.headerWithName(AUTHORIZATION).description("ACCESS TOKEN")
                        )
                        .responseFields(
                                fieldWithPath("message").description("메시지"),
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("data.storyId").description("스토리 id"))
                        .tag("Story")
                        .build()
        )));
    }


    @Test
    @DisplayName("스토리 전체 조회")
    void 스토리_전체조회() throws Exception{
        StoryResponse res1 = StoryResponse.of(1L,"http://1");
        StoryResponse res2 = StoryResponse.of(2L,"http://2");

        when(storyFacadeService.getAllStory()).thenReturn(List.of(res1,res2));

        ResultActions resultActions = mockMvc.perform(
                get("/story/all")
                        .header(AUTHORIZATION,BEARER+" "+JWT)
        ).andExpect(status().isOk());

        resultActions.andDo(restDocs.document(resource(
                ResourceSnippetParameters.builder()
                        .description("스토리 전체 조회 API")
                        .summary("스토리 전체 조회 API")
                        .requestHeaders(
                                headerWithName(AUTHORIZATION).description("요청자의 JWT")
                        )
                        .responseFields(
                                fieldWithPath("message").description("메시지"),
                                fieldWithPath("code").description("응답코드"),
                                fieldWithPath("data[].id").description("스토리 ID"),
                                fieldWithPath("data[].storyImageUrl").description("스토리 이미지 URL")
                        )
                        .tag("Story")
                        .build()
        )));
    }
}
