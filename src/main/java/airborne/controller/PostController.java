package airborne.controller;


import airborne.business.CreatePostUseCase;
import airborne.business.dto.CreatePostRequest;
import airborne.business.dto.CreatePostResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true" )
@AllArgsConstructor

public class PostController {

    private final CreatePostUseCase createPostUseCase;
@PostMapping()
public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request){
    CreatePostResponse response = createPostUseCase.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

}
