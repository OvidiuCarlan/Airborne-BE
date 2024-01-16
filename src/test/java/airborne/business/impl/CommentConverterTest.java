package airborne.business.impl;

import airborne.domain.Comment;
import airborne.persistance.entity.CommentEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentConverterTest {
    @Test
    void convert_CommentEntityToComment_Success() {
        // Arrange
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setPostId(2L);
        commentEntity.setUserId(3L);
        commentEntity.setContent("Test content");

        // Act
        Comment convertedComment = CommentConverter.convert(commentEntity);

        // Assert
        assertEquals(commentEntity.getId(), convertedComment.getId());
        assertEquals(commentEntity.getPostId(), convertedComment.getPostId());
        assertEquals(commentEntity.getUserId(), convertedComment.getUserId());
        assertEquals(commentEntity.getContent(), convertedComment.getContent());
    }
}