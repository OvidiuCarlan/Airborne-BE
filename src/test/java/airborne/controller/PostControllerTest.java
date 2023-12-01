package airborne.controller;

import airborne.business.*;
import airborne.business.dto.*;
import airborne.domain.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @Mock
    private CreatePostUseCase createPostUseCase;

    @Mock
    private GetPostUseCase getPostUseCase;

    @Mock
    private DeletePostUseCase deletePostUseCase;

    @Mock
    private UpdatePostUseCase updatePostUseCase;

    @Mock
    private GetUserPostsUseCase getUserPostsUseCase;

    @InjectMocks
    private PostController postController;

    @Test
    void testCreatePost_Success() {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        CreatePostResponse createPostResponse = new CreatePostResponse();

        when(createPostUseCase.createPost(any(CreatePostRequest.class))).thenReturn(createPostResponse);

        ResponseEntity<CreatePostResponse> responseEntity = postController.createPost(createPostRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createPostResponse, responseEntity.getBody());
    }

    @Test
    void testDeletePost_Success() {
        ResponseEntity<Void> responseEntity = postController.deletePost(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testUpdatePost_Success() {
        UpdatePostRequest updatePostRequest = new UpdatePostRequest();

        ResponseEntity<Void> responseEntity = postController.updatePost(1L, updatePostRequest);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testGetUserPosts_Success() {
        GetUserPostsRequest getUserPostsRequest = GetUserPostsRequest.builder().userId(1L).build();
        List<Post> posts = Collections.singletonList(new Post());

        GetUserPostsResponse getUserPostsResponse = new GetUserPostsResponse();
        getUserPostsResponse.setPosts(posts);

        when(getUserPostsUseCase.getUserPosts(any(GetUserPostsRequest.class))).thenReturn(getUserPostsResponse);

        ResponseEntity<GetUserPostsResponse> responseEntity = postController.getUserPosts(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(getUserPostsResponse, responseEntity.getBody());
    }
}