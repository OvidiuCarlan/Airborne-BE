package airborne.controller;


import airborne.business.*;
import airborne.business.dto.*;
import airborne.domain.Post;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true" )
@AllArgsConstructor

public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final GetPostUseCase getPostUseCase;
    private final DeletePostUseCase deletePostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final GetUserPostsUseCase getUserPostsUseCase;
    private final GetFeedPostsUseCase getFeedPostsUseCase;
    private final GetUserPostCountUseCase getUserPostCountUseCase;
    @PostMapping()
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request){
        CreatePostResponse response = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable long postId){
        deletePostUseCase.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity< Void> updatePost(@PathVariable("id") long id,
                                            @RequestBody @Valid UpdatePostRequest request){
        request.setId(id);
        updatePostUseCase.updatePost(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserPostsResponse> getUserPosts(
            @PathVariable(value = "id") final long id,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        GetUserPostsRequest request = GetUserPostsRequest.builder()
                .userId(id)
                .page(page)
                .build();
        GetUserPostsResponse response = getUserPostsUseCase.getUserPosts(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/feed/{userId}")
    public ResponseEntity<GetFeedPostsResponse> getFeedPosts(
            @PathVariable(value = "userId") final long userId,
            @RequestParam(value = "page", defaultValue = "0") int page){

        GetFeedPostsRequest request = GetFeedPostsRequest.builder()
                .userId(userId)
                .page(page)
                .build();
        GetFeedPostsResponse response = getFeedPostsUseCase.getFeedPosts(request);
        return ResponseEntity.ok(response);
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("statistics")
    public ResponseEntity<GetUserPostCountResponse> getUserPostCounts() {
        GetUserPostCountResponse response = getUserPostCountUseCase.getUserPostCount();
        return ResponseEntity.ok(response);
    }
}
