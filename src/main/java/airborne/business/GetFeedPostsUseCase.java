package airborne.business;

import airborne.business.dto.GetFeedPostsRequest;
import airborne.business.dto.GetFeedPostsResponse;

public interface GetFeedPostsUseCase {
    GetFeedPostsResponse getFeedPosts(GetFeedPostsRequest request);
}
