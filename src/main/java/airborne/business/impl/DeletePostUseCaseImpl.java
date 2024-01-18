package airborne.business.impl;

import airborne.business.DeletePostUseCase;
import airborne.persistance.CommentRepository;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.CommentEntity;
import airborne.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeletePostUseCaseImpl implements DeletePostUseCase {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void deletePost(Long postId) {
        PostEntity post = postRepository.findById(postId).orElse(null);

        if (post != null) {
            List<CommentEntity> comments = commentRepository.findAllByPostId(postId);
            commentRepository.deleteAll(comments);

            postRepository.delete(post);
        }
    }
}
