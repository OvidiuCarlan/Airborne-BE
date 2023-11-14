package airborne.business.impl;

import airborne.business.CreateUserUseCase;
import airborne.business.dto.CreateUserRequest;
import airborne.business.dto.CreateUserResponse;
import airborne.persistance.RoleRepository;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.RoleEnum;
import airborne.persistance.entity.UserEntity;
import airborne.persistance.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        UserEntity savedUser = savedNewUser(request);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    private UserEntity savedNewUser(CreateUserRequest request){
        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);
        request.setRole(role);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(role)
                .build();

        return userRepository.save(newUser);
    }
}
