package airborne.business.impl;

import airborne.business.dto.GetFriendListRequest;
import airborne.business.dto.GetFriendListResponse;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.RoleEnum;
import airborne.persistance.entity.UserEntity;
import airborne.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFriendListUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetFriendListUseCaseImpl getFriendListUseCase;

    @Test
    void getFriendList_WithUserId_Success() {
        // Arrange
        GetFriendListRequest request = new GetFriendListRequest(1L);
        List<UserEntity> friendEntities = new ArrayList<>();
        friendEntities.add(new UserEntity(2L, "Friend 1", "friend1@example.com", "Test", UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build()));
        friendEntities.add(new UserEntity(3L, "Friend 2", "friend2@example.com", "Test", UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build()));

        when(userRepository.getFriendList(any())).thenReturn(friendEntities);

        // Act
        GetFriendListResponse response = getFriendListUseCase.getFriendList(request);

        // Assert
        assertEquals(friendEntities.size(), response.getFriends().size());
        assertEquals("Friend 1", response.getFriends().get(0).getName());
        assertEquals("friend1@example.com", response.getFriends().get(0).getEmail());
        assertEquals("Friend 2", response.getFriends().get(1).getName());
        assertEquals("friend2@example.com", response.getFriends().get(1).getEmail());
    }

    @Test
    void getFriendList_NoFriends_ShouldReturnEmptyList() {
        // Arrange
        GetFriendListRequest request = new GetFriendListRequest(null);

        // Act
        GetFriendListResponse response = getFriendListUseCase.getFriendList(request);

        // Assert
        assertTrue(response.getFriends().isEmpty());
    }
}