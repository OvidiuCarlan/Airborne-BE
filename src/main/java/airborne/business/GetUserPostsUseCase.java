package airborne.business;

import airborne.business.dto.GetUserPostsRequest;
import airborne.business.dto.GetUserPostsResponse;

public interface GetUserPostsUseCase {
    GetUserPostsResponse getUserPosts(GetUserPostsRequest request);
}
