package airborne.business.impl;

import airborne.business.DeleteUserUseCase;
import airborne.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void DeleteUser(Long userId){this.userRepository.deleteById(userId);}
}
