package airborne.business;

import airborne.domain.CreateUserRequest;
import airborne.domain.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
