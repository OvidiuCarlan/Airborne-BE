package airborne.business;

import airborne.business.dto.CreatePostRequest;
import airborne.business.dto.CreatePostResponse;

public interface CreatePostUseCase {
    CreatePostResponse createPost(CreatePostRequest request);
}
