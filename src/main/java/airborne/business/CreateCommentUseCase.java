package airborne.business;

import airborne.business.dto.CreateCommentRequest;
import airborne.business.dto.CreateCommentResponse;

public interface CreateCommentUseCase {
    CreateCommentResponse createComment(CreateCommentRequest request);
}
