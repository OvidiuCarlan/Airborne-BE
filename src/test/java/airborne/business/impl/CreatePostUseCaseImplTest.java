package airborne.business.impl;

import airborne.business.dto.CreatePostRequest;
import airborne.business.dto.CreatePostResponse;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CreatePostUseCaseImplTest {

    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private CreatePostUseCaseImpl createPostUseCase;

    @Test
    void createPost_Success() {
        // Arrange
        CreatePostRequest request = new CreatePostRequest(1L, 1L, "Test content", "test-image", "2022-01-01T12:00:00");
        PostEntity newPostEntity = PostEntity.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .image(request.getImage())
                .dateTime(request.getDateTime())
                .build();

        when(postRepository.save(any())).thenReturn(newPostEntity);

        // Act
        CreatePostResponse response = createPostUseCase.createPost(request);

        // Assert
        assertEquals(newPostEntity.getId(), response.getPostId());
    }
}