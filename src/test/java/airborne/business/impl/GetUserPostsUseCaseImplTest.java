package airborne.business.impl;

import airborne.business.dto.GetUserPostsRequest;
import airborne.business.dto.GetUserPostsResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserPostsUseCaseImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetUserPostsUseCaseImpl getUserPostsUseCase;

    @Test
    void getUserPosts_Success() {
        // Arrange
        GetUserPostsRequest request = new GetUserPostsRequest(1L, 0);
        List<PostEntity> postEntities = new ArrayList<>(); // Add sample PostEntities as needed
        Pageable pageable = PageRequest.of(request.getPage(), 3, Sort.by("id").descending());
        Page<PostEntity> pageResult = new PageImpl<>(postEntities, pageable, 3);

        when(postRepository.getUserPostsPaginated(eq(1L), any())).thenReturn(pageResult);

        // Act
        GetUserPostsResponse response = getUserPostsUseCase.getUserPosts(request);

        // Assert
        assertEquals(postEntities.size(), response.getPosts().size());
    }
    @Test
    void getUserPosts_WithoutUserId_Failure() {
        // Arrange
        GetUserPostsRequest request = new GetUserPostsRequest(null, 0);

        // Act
        GetUserPostsResponse response = getUserPostsUseCase.getUserPosts(request);

        // Assert
        assertEquals(0, response.getPosts().size());
    }
}