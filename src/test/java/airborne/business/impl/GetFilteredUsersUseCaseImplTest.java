package airborne.business.impl;

import airborne.business.dto.GetFilteredUsersRequest;
import airborne.business.dto.GetFilteredUsersResponse;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
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
class GetFilteredUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetFilteredUsersUseCaseImpl getFilteredUsersUseCase;

    @Test
    void getFilteredUsers_WithRequest_Success() {
        // Arrange
        GetFilteredUsersRequest request = new GetFilteredUsersRequest("username");
        List<UserEntity> userEntities = new ArrayList<>();

        when(userRepository.filteredSearch(any())).thenReturn(userEntities);

        // Act
        GetFilteredUsersResponse response = getFilteredUsersUseCase.getFilteredUsers(request);

        // Assert
        assertEquals(userEntities.size(), response.getFilteredSearchUsers().size());
    }

    @Test
    void getFilteredUsers_NullRequest_Success() {
        // Arrange
        GetFilteredUsersRequest request = null;

        // Act
        GetFilteredUsersResponse response = getFilteredUsersUseCase.getFilteredUsers(request);

        // Assert
        assertEquals(0, response.getFilteredSearchUsers().size());
    }
}