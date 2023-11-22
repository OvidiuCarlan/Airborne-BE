package airborne.business.impl;

import airborne.domain.User;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void testGetUser() {
        // Arrange
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setName("John Doe");
        userEntity.setEmail("johndoe@example.com");
        userEntity.setPassword("password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        Optional<User> result = getUserUseCase.getUser(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        assertEquals("John Doe", result.get().getName());
        assertEquals("johndoe@example.com", result.get().getEmail());
        assertEquals("password", result.get().getPassword());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = getUserUseCase.getUser(userId);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }
}