package airborne.business.impl;

import airborne.business.DeleteUserUseCase;
import airborne.persistance.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;

        // Act
        deleteUserUseCase.DeleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}