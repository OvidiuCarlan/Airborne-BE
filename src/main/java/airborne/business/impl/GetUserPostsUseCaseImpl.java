package airborne.business.impl;

import airborne.business.GetUserPostsUseCase;
import airborne.business.dto.GetUserPostsRequest;
import airborne.business.dto.GetUserPostsResponse;
import airborne.domain.Post;
import airborne.persistance.PostRepository;
import airborne.persistance.entity.PostEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetUserPostsUseCaseImpl implements GetUserPostsUseCase {

    private final PostRepository postRepository;

    @Override
    public GetUserPostsResponse getUserPosts(GetUserPostsRequest request) {
        List<PostEntity> results;

        if(request.getUserId() != null){
            Pageable pageable = PageRequest.of(request.getPage(), 3,Sort.by("id").descending());
            Page<PostEntity> pageResult = postRepository.getUserPostsPaginated(request.getUserId(), pageable);

            results = pageResult.getContent();
        }
        else{
            results = new ArrayList<>();
        }
        final GetUserPostsResponse response = new GetUserPostsResponse();
        List<Post> posts = results
                .stream()
                .map(PostConverter::convert)
                .toList();
        response.setPosts(posts);

        return response;
    }
}
