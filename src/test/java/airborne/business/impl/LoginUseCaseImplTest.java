package airborne.business.impl;

import airborne.business.dto.LoginRequest;
import airborne.business.dto.LoginResponse;
import airborne.business.exception.InvalidCredentialsException;
import airborne.configuration.security.token.AccessTokenEncoder;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.RoleEnum;
import airborne.persistance.entity.UserEntity;
import airborne.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void login_Success() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        UserEntity user = new UserEntity(1L, "Test User", "test@example.com", "encodedPassword", UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build());

        when(userRepository.getUserEntityByEmail(any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn("mockedAccessToken");

        // Act
        LoginResponse response = loginUseCase.login(loginRequest);

        // Assert
        assertNotNull(response.getAccessToken());
        assertEquals("mockedAccessToken", response.getAccessToken());
    }

    @Test
    void login_InvalidCredentials_UserNotFound() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("nonexistent@example.com", "password");

        when(userRepository.getUserEntityByEmail(any())).thenReturn(null);

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
    }

    @Test
    void login_InvalidCredentials_PasswordMismatch() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("test@example.com", "wrongPassword");
        UserEntity user = new UserEntity(1L, "Test User", "test@example.com", "encodedPassword", UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build());

        when(userRepository.getUserEntityByEmail(any())).thenReturn(user);
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));
    }
}