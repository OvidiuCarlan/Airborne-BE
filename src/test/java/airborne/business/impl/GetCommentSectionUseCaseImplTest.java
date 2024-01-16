package airborne.business.impl;

import airborne.business.dto.GetCommentSectionRequest;
import airborne.business.dto.GetCommentSectionResponse;
import airborne.persistance.CommentRepository;
import airborne.persistance.entity.CommentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCommentSectionUseCaseImplTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private GetCommentSectionUseCaseImpl getCommentSectionUseCase;

    @Test
    void getCommentSection_Success() {
        // Arrange
        GetCommentSectionRequest request = new GetCommentSectionRequest(1L);
        CommentEntity comment1 = new CommentEntity(1L, 1L, 1L, "Comment 1");
        CommentEntity comment2 = new CommentEntity(2L, 1L, 2L, "Comment 2");
        List<CommentEntity> commentEntities = List.of(comment1, comment2);

        when(commentRepository.findAllByPostId(ArgumentMatchers.anyLong())).thenReturn(commentEntities);

        // Act
        GetCommentSectionResponse response = getCommentSectionUseCase.getCommentSection(request);

        // Assert
        assertEquals(commentEntities.size(), response.getComments().size());
        assertEquals(1L, response.getComments().get(0).getUserId());
        assertEquals("Comment 1", response.getComments().get(0).getContent());
        assertEquals(2L, response.getComments().get(1).getUserId());
        assertEquals("Comment 2", response.getComments().get(1).getContent());
    }

    @Test
    void getCommentSection_Failure() {
        // Arrange
        GetCommentSectionRequest request = new GetCommentSectionRequest(null);

        // Act
        GetCommentSectionResponse response = getCommentSectionUseCase.getCommentSection(request);

        // Assert
        assertEquals(0, response.getComments().size());
    }
}