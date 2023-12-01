package airborne.controller;

import airborne.business.*;
import airborne.business.dto.*;
import airborne.domain.User;
import airborne.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private GetUserUseCase getUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private LoginUseCase loginUseCase;

    @InjectMocks
    private UserController userController;
    @Test
    void testGetUser_Success() {
        // Mock data
        User mockUser = User.builder()
                .id(1L)
                .name("John Doe")
                .email("john@example.com")
                .password("password")
                .userRole(new UserRoleEntity())
                .build();
        Optional<User> optionalUser = Optional.of(mockUser);

        when(getUserUseCase.getUser(anyLong())).thenReturn(optionalUser);

        ResponseEntity<User> responseEntity = userController.getUser(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());
        User returnedUser = responseEntity.getBody();
        assertEquals("John Doe", returnedUser.getName());
        assertEquals("john@example.com", returnedUser.getEmail());
        assertEquals("password", returnedUser.getPassword());
    }

    @Test
    void testDeleteUser_Success() {
        doNothing().when(deleteUserUseCase).deleteUser(anyLong());

        ResponseEntity<Void> responseEntity = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(deleteUserUseCase).deleteUser(1L);
    }

    @Test
    void testCreateUser_Success() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        CreateUserResponse createUserResponse = new CreateUserResponse(123L);

        when(createUserUseCase.createUser(any(CreateUserRequest.class))).thenReturn(createUserResponse);

        ResponseEntity<CreateUserResponse> responseEntity = userController.createUser(createUserRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createUserResponse, responseEntity.getBody());
    }

    @Test
    void testUpdateUser_Success() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();

        ResponseEntity<Void> responseEntity = userController.updateUser(1L, updateUserRequest);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(updateUserUseCase).updateUser(any(UpdateUserRequest.class));
        verify(updateUserUseCase).updateUser(eq(updateUserRequest));
    }

    @Test
    void testLogin_Success() {
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse loginResponse = new LoginResponse();

        when(loginUseCase.login(any(LoginRequest.class))).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> responseEntity = userController.login(loginRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(loginResponse, responseEntity.getBody());
    }
}