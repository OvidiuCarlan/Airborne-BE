package airborne.business.impl;

import airborne.persistance.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetPostUseCaseImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetPostUseCaseImpl getPostUseCase;

    @Test
    void testGetPost(){

        //Arrange

    }
}