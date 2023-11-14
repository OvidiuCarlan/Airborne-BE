package airborne.business.impl;

import airborne.business.LoginUseCase;
import airborne.business.dto.LoginRequest;
import airborne.business.dto.LoginResponse;
import airborne.business.exception.InvalidCredentialsException;
import airborne.configuration.security.token.AccessTokenEncoder;
import airborne.configuration.security.token.impl.AccessTokenImpl;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.getUserEntityByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {

         return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getEmail(), user.getId(), List.of(user.getRole().getRole().toString())));
    }
}
