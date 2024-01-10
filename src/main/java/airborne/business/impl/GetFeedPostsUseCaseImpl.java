package airborne.business.impl;

import airborne.business.GetFeedPostsUseCase;
import airborne.business.dto.GetFeedPostsRequest;
import airborne.business.dto.GetFeedPostsResponse;
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
public class GetFeedPostsUseCaseImpl implements GetFeedPostsUseCase {
    private final PostRepository postRepository;

    @Override
    public GetFeedPostsResponse getFeedPosts(GetFeedPostsRequest request){
        List<PostEntity> results;

        if(request.getUserId() != null){
            Pageable pageable = PageRequest.of(request.getPage(), 5, Sort.by("id").descending());
            Page<PostEntity> pageResult = postRepository.getFeedPosts(request.getUserId(), pageable);

            results = pageResult.getContent();
        }
        else{
            results = new ArrayList<>();
        }
        final GetFeedPostsResponse response = new GetFeedPostsResponse();
        List<Post> posts = results
                .stream()
                .map(PostConverter::convert)
                .toList();
        response.setPosts(posts);

        return response;
    }
}
