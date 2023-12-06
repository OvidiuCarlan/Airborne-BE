package airborne.business;

import airborne.business.dto.AddFriendRequest;
import airborne.business.dto.AddFriendResponse;

public interface AddFriendUseCase {
    AddFriendResponse addFriend(AddFriendRequest request);
}
