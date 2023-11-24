package airborne.business;

import airborne.domain.Post;

import java.util.Optional;

public interface GetPostUseCase {
    Optional<Post> getPost(Long postId);
}
