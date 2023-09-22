package airborne.business.impl;

import airborne.business.GetUserUseCase;
import airborne.domain.User;
import airborne.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;
    @Override
    public Optional<User> getUser(Long userId) { return userRepository.findById(userId).map(UserConverter::convert); }
}
