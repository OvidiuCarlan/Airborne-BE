//package airborne.controller;
//
//import airborne.business.CreatePostUseCase;
//import airborne.business.DeletePostUseCase;
//import airborne.business.GetFeedPostsUseCase;
//import airborne.business.GetUserPostCountUseCase;
//import airborne.business.GetUserPostsUseCase;
//import airborne.business.UpdatePostUseCase;
//import airborne.business.dto.CreatePostRequest;
//import airborne.business.dto.CreatePostResponse;
//import airborne.configuration.security.token.AccessToken;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
//class PostControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private CreatePostUseCase createPostUseCase;
//
//    @MockBean
//    private DeletePostUseCase deletePostUseCase;
//
//    @MockBean
//    private UpdatePostUseCase updatePostUseCase;
//
//    @MockBean
//    private GetUserPostsUseCase getUserPostsUseCase;
//
//    @MockBean
//    private GetFeedPostsUseCase getFeedPostsUseCase;
//
//    @MockBean
//    private GetUserPostCountUseCase getUserPostCountUseCase;
//
//    @MockBean
//    private AccessToken authenticatedUser;
//
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = "USER")
//    void createPost_Success() throws Exception {
//        // Arrange
//        CreatePostRequest request = CreatePostRequest.builder()
//                .id(1L)
//                .userId(1L)
//                .content("Test content")
//                .image("test.jpg")
//                .dateTime("2024-01-19T12:00:00Z")
//                .build();
//
//        when(createPostUseCase.createPost(Mockito.any(CreatePostRequest.class)))
//                .thenReturn(CreatePostResponse.builder().postId(1L).build());
//
//        // Act
//        ResultActions result = mockMvc.perform(post("/posts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"id\":1,\"userId\":1,\"content\":\"Test content\",\"image\":\"test.jpg\",\"dateTime\":\"2024-01-19T12:00:00Z\"}"));
//
//        // Assert
//        result.andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.postId").value(1L));
//
//        verify(createPostUseCase, times(1)).createPost(request);
//    }
//
//
//}