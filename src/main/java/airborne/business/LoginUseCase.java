package airborne.business;

import airborne.business.dto.LoginRequest;
import airborne.business.dto.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}