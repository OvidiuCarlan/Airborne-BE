package airborne.controller;


import airborne.business.CreatePostUseCase;
import airborne.business.DeletePostUseCase;
import airborne.business.GetPostUseCase;
import airborne.business.UpdatePostUseCase;
import airborne.business.dto.CreatePostRequest;
import airborne.business.dto.CreatePostResponse;
import airborne.business.dto.UpdatePostRequest;
import airborne.domain.Post;
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
    @PostMapping()
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request){
        CreatePostResponse response = createPostUseCase.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") final long id){
        final Optional<Post> postOptional = getPostUseCase.getPost(id);
        if(postOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(postOptional.get());
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
}
