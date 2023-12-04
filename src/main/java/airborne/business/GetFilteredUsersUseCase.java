package airborne.business;

import airborne.business.dto.GetFilteredUsersRequest;
import airborne.business.dto.GetFilteredUsersResponse;

import java.util.List;

public interface GetFilteredUsersUseCase {

        GetFilteredUsersResponse getFilteredUsers(GetFilteredUsersRequest request);
}
