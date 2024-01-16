package airborne.controller;

import airborne.business.CreateCommentUseCase;
import airborne.business.GetCommentSectionUseCase;
import airborne.business.dto.CreateCommentRequest;
import airborne.business.dto.CreateCommentResponse;
import airborne.business.dto.GetCommentSectionRequest;
import airborne.business.dto.GetCommentSectionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.assertj.core.api.BDDAssertions.and;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCommentUseCase createCommentUseCase;

    @MockBean
    private GetCommentSectionUseCase getCommentSectionUseCase;

    @Test
    @WithMockUser(username = "user", password ="user", roles = "USER")
    void createComment_Success() throws Exception {
        when(createCommentUseCase.createComment(Mockito.any(CreateCommentRequest.class)))
                .thenReturn(CreateCommentResponse.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/comments/save")
                        .content("{\"postId\":1,\"userId\":1,\"content\":\"Test\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
//    @Test
//    @WithMockUser(username = "user", password ="user", roles = "USER")
//    void createComment_Success() throws Exception {
//        String json = "{\n" +
//                "    \"postId\": 1,\n" +
//                "    \"userId\": 1,\n" +
//                "    \"content\": \"test\"\n" +
//                "}";
//
//        mockMvc.perform(post("/comments/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isCreated());
//    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void getCommentSection_Success() throws Exception {
        when(getCommentSectionUseCase.getCommentSection(Mockito.any(GetCommentSectionRequest.class)))
                .thenReturn(GetCommentSectionResponse.builder().comments(Collections.emptyList()).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/1"))
                .andExpect(status().isOk());
    }
}