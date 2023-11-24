package airborne.business.impl;

import airborne.business.UpdatePostUseCase;
import airborne.business.dto.UpdatePostRequest;
import airborne.business.exception.InvalidPostIdException;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatePostUseCaseImpl implements UpdatePostUseCase {
    private final PostRepository postRepository;

    @Override
    public void updatePost(UpdatePostRequest request){
        Optional<PostEntity> postOptional = postRepository.findById(request.getId());

        if(postOptional.isEmpty()){
            throw new InvalidPostIdException("POST_ID_INVALID");
        }
        PostEntity post = postOptional.get();
        updateFields(request, post);
        postRepository.save(post);
    }
    private void updateFields(UpdatePostRequest request, PostEntity post){
        post.setUserId(request.getUserId());
        post.setContent(request.getContent());
        post.setImage(request.getImage());
    }
}
