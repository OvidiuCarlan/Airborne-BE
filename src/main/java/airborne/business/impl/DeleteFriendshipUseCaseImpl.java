package airborne.business.impl;

import airborne.business.DeleteFriendshipUseCase;
import airborne.persistance.FriendshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteFriendshipUseCaseImpl implements DeleteFriendshipUseCase {

    private final FriendshipRepository friendshipRepository;

    @Override
    public void deleteFriendship(Long id){
        friendshipRepository.deleteById(id);
    }
}
