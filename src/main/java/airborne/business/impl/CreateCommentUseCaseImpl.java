package airborne.business.impl;

import airborne.business.CreateCommentUseCase;
import airborne.business.dto.CreateCommentRequest;
import airborne.business.dto.CreateCommentResponse;
import airborne.persistance.CommentRepository;
import airborne.persistance.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCommentUseCaseImpl implements CreateCommentUseCase {
    private final CommentRepository commentRepository;

    @Override
    public CreateCommentResponse createComment(CreateCommentRequest request){
        CommentEntity savedComment = savedNewComment(request);

        return CreateCommentResponse.builder()
                .id(savedComment.getId())
                .build();
    }

    private CommentEntity savedNewComment(CreateCommentRequest request){
        CommentEntity newComment = CommentEntity.builder()
                .postId(request.getPostId())
                .userId(request.getUserId())
                .content(request.getContent())
                .build();
        return commentRepository.save(newComment);
    }
}
