package airborne.business;

import airborne.business.dto.CreateUserRequest;
import airborne.business.dto.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
