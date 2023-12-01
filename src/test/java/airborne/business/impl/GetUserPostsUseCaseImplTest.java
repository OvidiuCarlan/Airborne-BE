package airborne.business.impl;

import airborne.business.dto.GetUserPostsRequest;
import airborne.business.dto.GetUserPostsResponse;
import airborne.domain.Post;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserPostsUseCaseImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetUserPostsUseCaseImpl getUserPostsUseCase;

    @Test
    void GetUserPosts(){

        List<PostEntity> mockedPostEntities = new ArrayList<>();
        mockedPostEntities.add(new PostEntity());

        when(postRepository.getPostEntityByUserId(anyLong())).thenReturn(mockedPostEntities);

        GetUserPostsRequest request = new GetUserPostsRequest();
        request.setUserId(123L);

        GetUserPostsResponse response = getUserPostsUseCase.getUserPosts(request);

        assertEquals(1, response.getPosts().size());
    }
}