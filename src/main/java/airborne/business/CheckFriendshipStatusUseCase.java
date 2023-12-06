package airborne.business;

import airborne.business.dto.CheckFriendshipStatusRequest;
import airborne.business.dto.CheckFriendshipStatusResponse;

public interface CheckFriendshipStatusUseCase {
    CheckFriendshipStatusResponse checkFriendshipStatus(CheckFriendshipStatusRequest request);
}
