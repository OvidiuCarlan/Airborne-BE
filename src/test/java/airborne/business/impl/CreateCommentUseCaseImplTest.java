package airborne.business.impl;

import airborne.business.dto.CreateCommentRequest;
import airborne.business.dto.CreateCommentResponse;
import airborne.persistance.CommentRepository;
import airborne.persistance.entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCommentUseCaseImplTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CreateCommentUseCaseImpl createCommentUseCase;

    @Test
    void createComment_Success() {
        // Arrange
        CreateCommentRequest request = new CreateCommentRequest(1L, 2L, "Test content");
        CommentEntity newCommentEntity = CommentEntity.builder()
                .postId(request.getPostId())
                .userId(request.getUserId())
                .content(request.getContent())
                .build();

        when(commentRepository.save(any())).thenReturn(newCommentEntity);

        // Act
        CreateCommentResponse response = createCommentUseCase.createComment(request);

        // Assert
        assertEquals(newCommentEntity.getId(), response.getId());
    }
}