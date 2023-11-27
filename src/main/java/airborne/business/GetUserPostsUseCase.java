package airborne.business;

import airborne.business.dto.GetUserPostsRequest;
import airborne.business.dto.GetUserPostsResponse;
import airborne.domain.Post;

import java.util.List;

public interface GetUserPostsUseCase {
    GetUserPostsResponse getUserPosts(GetUserPostsRequest request);
}
