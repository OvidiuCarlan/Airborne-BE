package airborne.business.impl;

import airborne.persistance.CommentRepository;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.CommentEntity;
import airborne.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletePostUseCaseImplTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private DeletePostUseCaseImpl deletePostUseCase;

    @Test
    void deletePost_Success() {

        //Arrange
        Long postId = 1L;

        PostEntity postEntity = new PostEntity();
        postEntity.setId(postId);

        List<CommentEntity> comments = Arrays.asList(
                new CommentEntity(1L, 1L, 1L, "Comment 1"),
                new CommentEntity(2L, 2L, 2L, "Comment 2")
        );

        when(postRepository.findById(postId)).thenReturn(Optional.of(postEntity));
        when(commentRepository.findAllByPostId(postId)).thenReturn(comments);

        //Act
        deletePostUseCase.deletePost(postId);

        // Assert
        verify(commentRepository, times(1)).deleteAll(comments);
        verify(postRepository, times(1)).delete(postEntity);
    }
    @Test
    void deletePost_PostNotFound() {

        //Arrange
        Long postId = 1L;

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act
        deletePostUseCase.deletePost(postId);

        // Assert
        verify(commentRepository, never()).deleteAll(anyList());
        verify(postRepository, never()).delete(any());
    }
}