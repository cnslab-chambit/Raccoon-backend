package kwu.raccoonapi.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import kwu.raccoonapi.controller.api.user.UserProfileController;
import kwu.raccoonapi.document.utils.ApiDocumentationTest;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static kwu.raccooncommon.consts.CommonConsts.BEARER;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserProfileControllerTest extends ApiDocumentationTest {

    @InjectMocks
    UserProfileController userProfileController;

    @Mock
    UserProfileFacadeService userProfileFacadeService;

    @BeforeEach
    void setup(RestDocumentationContextProvider provider){
        super.setup(userProfileController,provider);
    }

    @Test
    @DisplayName("유저프로필조회")
    public void 프로필_조회() throws Exception {
        when(userProfileFacadeService.getProfile(anyLong())).thenReturn(
                UserProfileDetailsResponse.of(
                       "오어어엉",
                       "https://www.google.com/imgres?imgurl=https%3A%2F%2Ftalkimg.imbc.com%2FTVianUpload%2Ftvian%2FTViews%2Fimage%2F2023%2F01%2F03%2F7eec0933-d37b-4939-afb4-f1488ebc0d68.jpg&tbnid=A4jvGsqs01f0aM&vet=12ahUKEwiFrPSB9vT9AhWCdt4KHSIgABUQMygHegUIARDZAQ..i&imgrefurl=https%3A%2F%2Fimnews.imbc.com%2Fnews%2F2023%2Fenter%2Farticle%2F6442122_36161.html&docid=xWL2UuU60SIGKM&w=800&h=1119&q=%ED%95%B4%EB%A6%B0&ved=2ahUKEwiFrPSB9vT9AhWCdt4KHSIgABUQMygHegUIARDZAQ",
                        Gender.WOMAN,
                        18L,
                        167L,
                        "모름",
                        Location.GANDONG,
                        "안녕하세요",
                        false,
                        Mbti.INTP,
                        Animal.CAT,
                        Animal.DEER
                )
        );

        ResultActions resultActions = mockMvc.perform(get("/user/{userId}",1L)
                .header(AUTHORIZATION,BEARER+" "+JWT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andDo(restDocs.document(resource(
                ResourceSnippetParameters.builder()
                        .description("유저프로필 조회 API")
                        .requestHeaders(
                                headerWithName(AUTHORIZATION).description("요청보낸 사람 ACCESS TOKEN")
                        )
                        .pathParameters(
                                parameterWithName("userId").description("대상 유저의Id")
                        )
                        .responseFields(
                                fieldWithPath("message").description("결과 메시지"),
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("data.nickname").description("프로필 닉네임"),
                                fieldWithPath("data.profileImageUrl").description("프로필 닉네임"),
                                fieldWithPath("data.gender").description("프로필 닉네임"),
                                fieldWithPath("data.age").description("프로필 닉네임"),
                                fieldWithPath("data.height").description("프로필 닉네임"),
                                fieldWithPath("data.job").description("프로필 닉네임"),
                                fieldWithPath("data.location").description("프로필 닉네임"),
                                fieldWithPath("data.selfDescription").description("프로필 닉네임"),
                                fieldWithPath("data.smokingStatus").description("프로필 닉네임"),
                                fieldWithPath("data.mbti").description("프로필 닉네임"),
                                fieldWithPath("data.animal").description("프로필 닉네임"),
                                fieldWithPath("data.wantedAnimal").description("프로필 닉네임"))
                        .tag("UserProfile")
                        .build()
        )));
    }

    @Test
    @DisplayName("프로필 업데이트")
    void 프로필_업데이트() throws Exception{
        UserProfileUpdateResponse response = UserProfileUpdateResponse.of(1L);

        when(userProfileFacadeService.updateProfile(any()))
                .thenReturn(response);

        MockMultipartHttpServletRequestBuilder builder = multipart("/user/profile");
        builder.with(request -> {
            request.setMethod(PATCH.name());
            return request;
        });

        MockMultipartFile profileImage = new MockMultipartFile("profileImage","image.jpg".getBytes());

        ResultActions resultActions = mockMvc.perform(builder
                .file(profileImage)
                .param("nickname","닉네임")
                .param("gender",Gender.MAN.name())
                .param("age","24")
                .param("height","175")
                .param("selfDescription","안녕하세요 자기소개입니다.")
                .param("smokingStatus","false")
                .param("mbti",Mbti.INTP.name())
                .param("animal",Animal.BEAR.name())
                .param("wantedAnimal",Animal.BEAR.name())
                .param("job","직업은 이래요")
                .param("location",Location.DONGJAK.name())
                .header(AUTHORIZATION,BEARER+" "+JWT)
                .contentType(MULTIPART_FORM_DATA))
                .andExpect(status().isOk());

        resultActions.andDo(restDocs.document(resource(
                ResourceSnippetParameters.builder()
                        .description("프로필 변경 API")
                        .summary("프로필 변경 API")
                        .requestParameters(
                                parameterWithName("nickname").description("닉네임"),
                                parameterWithName("gender").description("성별"),
                                parameterWithName("age").description("나이"),
                                parameterWithName("height").description("키"),
                                parameterWithName("selfDescription").description("자기소개"),
                                parameterWithName("smokingStatus").description("흡연여부"),
                                parameterWithName("mbti").description("mbti"),
                                parameterWithName("animal").description("유저의 동물상"),
                                parameterWithName("wantedAnimal").description("원하는 동물상"),
                                parameterWithName("job").description("직업"),
                                parameterWithName("location").description("사는 곳")
                        )
                        .requestHeaders(
                                headerWithName(AUTHORIZATION).description("JWT")
                        )
                        .responseFields(
                                fieldWithPath("message").description("메시지"),
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("data.userId").description("유저의 프로필 id"))
                        .tag("UserProfile")
                        .build()
        )));
    }
}
