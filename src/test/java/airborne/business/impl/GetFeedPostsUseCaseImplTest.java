package airborne.business.impl;

import airborne.business.dto.GetFeedPostsRequest;
import airborne.business.dto.GetFeedPostsResponse;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFeedPostsUseCaseImplTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetFeedPostsUseCaseImpl getFeedPostsUseCase;

    @Test
    void getFeedPosts_WithUserId_Success() {
        // Arrange
        GetFeedPostsRequest request = new GetFeedPostsRequest(1L, 0);

        PostEntity post1 = new PostEntity(1L, 1L, "Post 1", "Image1", null);
        PostEntity post2 = new PostEntity(2L, 2L, "Post 2", "Image2", null);
        List<PostEntity> postEntities = List.of(post1, post2);

        Pageable pageable = PageRequest.of(request.getPage(), 5, Sort.by("id").descending());
        Page<PostEntity> pageResult = new PageImpl<>(postEntities, pageable, 5);

        when(postRepository.getFeedPosts(anyLong(), any())).thenReturn(pageResult);

        // Act
        GetFeedPostsResponse response = getFeedPostsUseCase.getFeedPosts(request);

        // Assert
        assertEquals(postEntities.size(), response.getPosts().size());
        assertEquals(1L, response.getPosts().get(0).getUserId());
        assertEquals("Post 1", response.getPosts().get(0).getContent());
        assertEquals(2L, response.getPosts().get(1).getUserId());
        assertEquals("Post 2", response.getPosts().get(1).getContent());
    }

    @Test
    void getFeedPosts_Failure() {
        // Arrange
        GetFeedPostsRequest request = new GetFeedPostsRequest(null, 0);

        // Act
        GetFeedPostsResponse response = getFeedPostsUseCase.getFeedPosts(request);

        // Assert
        assertEquals(0, response.getPosts().size());
    }
}