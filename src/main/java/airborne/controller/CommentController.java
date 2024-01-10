package airborne.controller;

import airborne.business.CreateCommentUseCase;
import airborne.business.GetCommentSectionUseCase;
import airborne.business.dto.CreateCommentRequest;
import airborne.business.dto.CreateCommentResponse;
import airborne.business.dto.GetCommentSectionRequest;
import airborne.business.dto.GetCommentSectionResponse;
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
    private final GetCommentSectionUseCase getCommentSectionUseCase;

    @PostMapping(path = "/save")
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody @Valid CreateCommentRequest request){
        CreateCommentResponse response = createCommentUseCase.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "{postId}")
    public ResponseEntity<GetCommentSectionResponse> getCommentSection(@PathVariable(value = "postId") final long postId){
        GetCommentSectionRequest request = GetCommentSectionRequest.builder()
                .postId(postId)
                .build();
        GetCommentSectionResponse response = getCommentSectionUseCase.getCommentSection(request);
        return  ResponseEntity.ok(response);
    }

}
