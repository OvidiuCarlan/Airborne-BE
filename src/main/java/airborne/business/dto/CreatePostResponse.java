package airborne.business.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePostResponse {
    private Long postId;
}
