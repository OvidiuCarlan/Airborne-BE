package airborne.business;

import airborne.business.dto.GetFilteredUsersRequest;
import airborne.business.dto.GetFilteredUsersResponse;

public interface GetFilteredUsersUseCase {

        GetFilteredUsersResponse getFilteredUsers(GetFilteredUsersRequest request);
}
