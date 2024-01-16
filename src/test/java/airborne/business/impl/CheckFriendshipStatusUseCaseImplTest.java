package airborne.business.impl;

import airborne.business.CheckFriendshipStatusUseCase;
import airborne.business.dto.CheckFriendshipStatusRequest;
import airborne.business.dto.CheckFriendshipStatusResponse;
import airborne.persistance.FriendshipRepository;
import airborne.persistance.entity.FriendshipEntity;
import airborne.persistance.entity.FriendshipEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckFriendshipStatusUseCaseImplTest {
    @Mock
    private FriendshipRepository friendshipRepository;

    @InjectMocks
    private CheckFriendshipStatusUseCaseImpl checkFriendshipStatusUseCase;

    @Test
    void checkFriendshipStatus_Friends() {
        // Arrange
        CheckFriendshipStatusRequest request = new CheckFriendshipStatusRequest(1L, 2L);
        FriendshipEntity friendshipEntity = new FriendshipEntity();
        friendshipEntity.setId(1L);
        friendshipEntity.setStatus(FriendshipEnum.ACCEPTED);

        when(friendshipRepository.getFriendshipBySenderIdAndRecipientId(anyLong(), anyLong())).thenReturn(friendshipEntity);

        // Act
        CheckFriendshipStatusResponse response = checkFriendshipStatusUseCase.checkFriendshipStatus(request);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals(FriendshipEnum.ACCEPTED, response.getStatus());
    }
    @Test
    void checkFriendshipStatus_NotFriends() {
        // Arrange
        CheckFriendshipStatusRequest request = new CheckFriendshipStatusRequest(3L, 4L);

        when(friendshipRepository.getFriendshipBySenderIdAndRecipientId(anyLong(), anyLong())).thenReturn(null);

        // Act
        CheckFriendshipStatusResponse response = checkFriendshipStatusUseCase.checkFriendshipStatus(request);

        // Assert
        assertEquals(0L, response.getId());
        assertEquals(FriendshipEnum.NOT_FRIENDS, response.getStatus());
    }

    @Test
    void checkFriendshipStatus_InvalidUsers() {
        // Arrange
        CheckFriendshipStatusRequest request = new CheckFriendshipStatusRequest(0L, 0L);

        // Act
        CheckFriendshipStatusResponse response = checkFriendshipStatusUseCase.checkFriendshipStatus(request);

        // Assert
        assertEquals(0L, response.getId());
        assertEquals(FriendshipEnum.NOT_FRIENDS, response.getStatus());
    }
}