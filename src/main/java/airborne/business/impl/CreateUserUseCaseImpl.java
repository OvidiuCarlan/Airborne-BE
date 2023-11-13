package airborne.business.impl;

import airborne.business.CreateUserUseCase;
import airborne.business.dto.CreateUserRequest;
import airborne.business.dto.CreateUserResponse;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        UserEntity savedUser = savedNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private UserEntity savedNewUser(CreateUserRequest request){
        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        return userRepository.save(newUser);
    }
}
