package airborne.business.impl;

import airborne.business.GetPostUseCase;
import airborne.domain.Post;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPostUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetPostUseCaseImpl getPostUseCase;

    @Test
    void testGetPost_WhenPostExists() {
        Long postId = 1L;
        PostEntity mockPost = new PostEntity();
        mockPost.setId(postId);



        when(postRepository.findById(postId)).thenReturn(Optional.of(mockPost));

        Optional<Post> result = getPostUseCase.getPost(postId);

        assertTrue(result.isPresent());
        assertEquals(postId, result.get().getId());
    }

    @Test
    void testGetPost_WhenPostDoesNotExist() {
        Long nonExistentPostId = 999L;

        when(postRepository.findById(nonExistentPostId)).thenReturn(Optional.empty());

        Optional<Post> result = getPostUseCase.getPost(nonExistentPostId);

        assertTrue(result.isEmpty());
    }
}