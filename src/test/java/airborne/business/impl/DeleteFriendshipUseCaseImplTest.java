package airborne.business.impl;

import airborne.persistance.FriendshipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteFriendshipUseCaseImplTest {
    @Mock
    private FriendshipRepository friendshipRepository;

    @InjectMocks
    private DeleteFriendshipUseCaseImpl deleteFriendshipUseCase;

    @Test
    void deleteFriendship_Success() {
        // Arrange
        Long friendshipId = 1L;

        // Mocking the deleteById method to do nothing when called
        doNothing().when(friendshipRepository).deleteById(any());

        // Act
        deleteFriendshipUseCase.deleteFriendship(friendshipId);

        // Assert
        verify(friendshipRepository).deleteById(friendshipId);
    }
}