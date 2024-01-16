package airborne.business.impl;

import airborne.business.CheckFriendshipStatusUseCase;
import airborne.business.dto.CheckFriendshipStatusRequest;
import airborne.business.dto.CheckFriendshipStatusResponse;
import airborne.domain.Friendship;
import airborne.persistance.FriendshipRepository;
import airborne.persistance.entity.FriendshipEntity;
import airborne.persistance.entity.FriendshipEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CheckFriendshipStatusUseCaseImpl implements CheckFriendshipStatusUseCase {

    private final FriendshipRepository friendshipRepository;
    @Override
    public CheckFriendshipStatusResponse checkFriendshipStatus(CheckFriendshipStatusRequest request){
        FriendshipEntity friendshipEntity;

        if (request.getLoggedInUser() != 0L && request.getOtherUser() != 0L) {
            friendshipEntity = friendshipRepository.getFriendshipBySenderIdAndRecipientId(request.getLoggedInUser(), request.getOtherUser());

            if(friendshipEntity == null){
                friendshipEntity = new FriendshipEntity();
                friendshipEntity.setId(0L);
                friendshipEntity.setStatus(FriendshipEnum.NOT_FRIENDS);
            }
        }
        else {
            friendshipEntity = new FriendshipEntity();
            friendshipEntity.setId(0L);
            friendshipEntity.setStatus(FriendshipEnum.NOT_FRIENDS);
        }
        final CheckFriendshipStatusResponse response = new CheckFriendshipStatusResponse();
        Friendship friendship = FriendshipConverter.convert(friendshipEntity);
        response.setId(friendship.getId());
        response.setStatus(friendship.getStatus());

        return response;
    }

}
