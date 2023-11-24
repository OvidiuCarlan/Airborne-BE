package airborne.business.impl;

import airborne.business.CreatePostUseCase;
import airborne.business.dto.CreatePostRequest;
import airborne.business.dto.CreatePostResponse;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePostUseCaseImpl implements CreatePostUseCase {

    private  final PostRepository postRepository;

    @Override
    public CreatePostResponse createPost(CreatePostRequest request){

        PostEntity savedPost = savedNewPost(request);

        return CreatePostResponse.builder()
                .postId(savedPost.getId())
                .build();
    }

    private PostEntity savedNewPost(CreatePostRequest request){

        PostEntity newPost = PostEntity.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .image(request.getImage())
                .build();
        return postRepository.save(newPost);
    }
}
