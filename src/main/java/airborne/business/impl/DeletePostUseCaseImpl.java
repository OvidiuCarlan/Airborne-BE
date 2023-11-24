package airborne.business.impl;

import airborne.business.DeletePostUseCase;
import airborne.persistance.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePostUseCaseImpl implements DeletePostUseCase {
    private final PostRepository postRepository;

    @Override
    public void deletePost(Long postId){this.postRepository.deleteById(postId);}
}
