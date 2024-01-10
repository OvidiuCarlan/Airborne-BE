package airborne.business.impl;

import airborne.business.GetCommentSectionUseCase;
import airborne.business.dto.GetCommentSectionRequest;
import airborne.business.dto.GetCommentSectionResponse;
import airborne.domain.Comment;
import airborne.persistance.CommentRepository;
import airborne.persistance.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCommentSectionUseCaseImpl implements GetCommentSectionUseCase {

    private final CommentRepository commentRepository;
    @Override
    public GetCommentSectionResponse getCommentSection(GetCommentSectionRequest request){
        List<CommentEntity> results;

        if(request.getPostId() != null){
            results = commentRepository.findAllByPostId(request.getPostId());
        }
        else {
            results = new ArrayList<>();
        }
        final GetCommentSectionResponse response = new GetCommentSectionResponse();
        List<Comment> comments = results
                .stream()
                .map(CommentConverter::convert)
                .toList();
        response.setComments(comments);

        return response;
    }
}
