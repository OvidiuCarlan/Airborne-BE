package airborne.business.impl;

import airborne.business.dto.GetUserPostCountResponse;
import airborne.domain.UserPostCount;
import airborne.persistance.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserPostCountUseCaseImplTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    GetUserPostCountUseCaseImpl getUserPostCountUseCase;

    @Test
    public void GetUserPostCount(){
        when(postRepository.getUserPostCounts()).thenReturn(Collections.singletonList(new UserPostCount()));

        GetUserPostCountResponse response = getUserPostCountUseCase.getUserPostCount();

        assertEquals(1, response.getUserPostCounts().size());
    }
}