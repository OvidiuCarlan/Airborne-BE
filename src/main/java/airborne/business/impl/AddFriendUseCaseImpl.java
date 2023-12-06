package airborne.business.impl;

import airborne.business.AddFriendUseCase;
import airborne.business.dto.AddFriendRequest;
import airborne.business.dto.AddFriendResponse;
import airborne.persistance.FriendshipRepository;
import airborne.persistance.entity.FriendshipEntity;
import airborne.persistance.entity.FriendshipEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddFriendUseCaseImpl implements AddFriendUseCase {


    private final FriendshipRepository friendshipRepository;
    @Override
    public AddFriendResponse addFriend(AddFriendRequest request){

        FriendshipEntity savedFriendship = savedNewFriendship(request);

        return AddFriendResponse.builder()
                .id(savedFriendship.getId())
                .status(savedFriendship.getStatus())
                .build();
    }
    private FriendshipEntity savedNewFriendship(AddFriendRequest request){
        FriendshipEntity newFriendship = FriendshipEntity.builder()
                .senderId(request.getSenderId())
                .recipientId(request.getRecipientId())
                .status(FriendshipEnum.ACCEPTED)
                .build();

        return friendshipRepository.save(newFriendship);
    }
}
