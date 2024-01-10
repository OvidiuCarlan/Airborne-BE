package airborne.business;

import airborne.business.dto.GetCommentSectionRequest;
import airborne.business.dto.GetCommentSectionResponse;

public interface GetCommentSectionUseCase {
    GetCommentSectionResponse getCommentSection(GetCommentSectionRequest request);
}
