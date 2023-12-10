package airborne.controller;

import airborne.business.CreateCommentUseCase;
import airborne.business.dto.CreateCommentRequest;
import airborne.business.dto.CreateCommentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true" )
@AllArgsConstructor
public class CommentController {
    private final CreateCommentUseCase createCommentUseCase;

    @PostMapping(path = "/save")
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody @Valid CreateCommentRequest request){
        CreateCommentResponse response = createCommentUseCase.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
