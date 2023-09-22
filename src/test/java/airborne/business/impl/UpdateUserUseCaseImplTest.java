package airborne.business.impl;

import airborne.business.UpdateUserUseCase;
import airborne.business.exception.InvalidUserIdException;
import airborne.domain.UpdateUserRequest;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    public void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        String newUsername = "John Doe";
        String newEmail = "johndoe@example.com";
        String newPassword = "newpassword";

        UpdateUserRequest request = new UpdateUserRequest(userId, newUsername, newEmail, newPassword);

        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setName("Old Name");
        existingUser.setEmail("oldemail@example.com");
        existingUser.setPassword("oldpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act
        updateUserUseCase.updateUser(request);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(existingUser);

        assertEquals(newUsername, existingUser.getName());
        assertEquals(newEmail, existingUser.getEmail());
        assertEquals(newPassword, existingUser.getPassword());
    }
    @Test
    public void testUpdateUserInvalidUserId() {
        // Arrange
        Long userId = 1L;
        UpdateUserRequest request = new UpdateUserRequest(userId, "John Doe", "johndoe@example.com", "newpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidUserIdException.class, () -> updateUserUseCase.updateUser(request));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}