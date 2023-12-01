package airborne.business.impl;

import airborne.persistance.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletePostUseCaseImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private DeletePostUseCaseImpl deletePostUseCase;

    @Test
    void DeletePost(){
        //Arrange
        Long postId = 1L;

        //Act
        deletePostUseCase.deletePost(postId);

        //Assert
        verify(postRepository, times(1)).deleteById(postId);
    }

}