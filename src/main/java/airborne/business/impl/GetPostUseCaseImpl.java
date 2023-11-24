package airborne.business.impl;

import airborne.business.GetPostUseCase;
import airborne.domain.Post;
import airborne.persistance.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPostUseCaseImpl implements GetPostUseCase {
    private final PostRepository postRepository;

    @Override
    public Optional<Post> getPost(Long postId) { return postRepository.findById(postId).map(PostConverter::convert);}
}
