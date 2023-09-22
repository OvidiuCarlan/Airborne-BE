package airborne.business.impl;

import airborne.business.CreateUserUseCase;
import airborne.domain.CreateUserRequest;
import airborne.domain.CreateUserResponse;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    public void CreateUserShouldCreateNewUser() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest(1L,"John Doe", "johndoe@example.com", "password");

        UserEntity savedUser = new UserEntity();
        savedUser.setId(1L);

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        // Act
        CreateUserResponse response = createUserUseCase.createUser(request);

        // Assert
        verify(userRepository, times(1)).save(any(UserEntity.class));
        assert(response.getUserId() == 1L);
    }
}