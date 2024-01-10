//package airborne.business.impl;
//
//import airborne.business.dto.CreatePostRequest;
//import airborne.business.dto.CreatePostResponse;
//import airborne.persistance.PostRepository;
//import airborne.persistance.entity.PostEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class CreatePostUseCaseImplTest {
//
//    @Mock
//    private PostRepository postRepository;
//    @InjectMocks
//    private CreatePostUseCaseImpl createPostUseCase;
//
//    @Test
//    void testCreatePostShouldCreateNewPost() {
//        // Arrange
//        CreatePostRequest request = new CreatePostRequest(1L, 1L, "Post content", "image_url");
//
//        PostEntity savedPost = new PostEntity();
//        savedPost.setId(1L);
//
//        PostEntity postEntity = PostEntity.builder()
//                .id(1L)
//                .userId(1L)
//                .content("Test Post")
//                .image("Test_Image")
//                .build();
//
//        when(postRepository.save(any(PostEntity.class))).thenReturn(savedPost);
//
//        // Act
//        CreatePostResponse response = createPostUseCase.createPost(request);
//
//        // Assert
//        verify(postRepository, times(1)).save(any(PostEntity.class));
//        assertEquals(1L, response.getPostId());
//    }
//}