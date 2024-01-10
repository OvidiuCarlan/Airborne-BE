package airborne.business;

import airborne.business.dto.GetFriendListRequest;
import airborne.business.dto.GetFriendListResponse;

public interface GetFriendListUseCase {
    GetFriendListResponse getFriendList (GetFriendListRequest request);
}
