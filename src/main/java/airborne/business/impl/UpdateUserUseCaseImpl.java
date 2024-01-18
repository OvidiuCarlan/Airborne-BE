package airborne.business.impl;

import airborne.business.UpdateUserUseCase;
import airborne.business.exception.InvalidUserIdException;
import airborne.business.dto.UpdateUserRequest;
import airborne.persistance.UserRepository;
import airborne.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void updateUser(UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findById(request.getId());

        if(userOptional.isEmpty()){
            throw new InvalidUserIdException("USER_ID_INVALID");
        }
        UserEntity user = userOptional.get();
        updateFields(request, user);
        userRepository.save(user);
    }
    private void updateFields(UpdateUserRequest request, UserEntity user){
        user.setName(request.getName());
        user.setEmail(request.getEmail());
    }
}
