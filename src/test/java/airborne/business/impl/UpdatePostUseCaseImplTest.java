package airborne.business.impl;

import airborne.business.dto.UpdatePostRequest;
import airborne.business.exception.InvalidPostIdException;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdatePostUseCaseImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UpdatePostUseCaseImpl updatePostUseCase;

    @Test
    void testUpdatePost_Success(){

        //Arrange
        Long postId = 1L;
        Long userId = 123L;

        UpdatePostRequest request = new UpdatePostRequest();
        request.setId(postId);
        request.setUserId(userId);
        request.setContent("Updated Content");
        request.setImage("Updated Image");

        PostEntity existingPost = new PostEntity();
        existingPost.setId(postId);
        existingPost.setUserId(userId);
        existingPost.setContent("Old Content");
        existingPost.setImage("Old Image");

        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));

        //Act
        updatePostUseCase.updatePost(request);

        //Assert
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(existingPost);

        assertEquals(request.getUserId(), existingPost.getUserId());
        assertEquals(request.getContent(), existingPost.getContent());
        assertEquals(request.getImage(), existingPost.getImage());
    }

    @Test
    void testUpdatePost_InvalidPostId(){

        //Arrange
        Long postId = 1L;
        UpdatePostRequest request = new UpdatePostRequest(postId, 123L, "Update", "Image");

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(InvalidPostIdException.class, () -> updatePostUseCase.updatePost(request));

        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, never()).save(any(PostEntity.class));
    }
}