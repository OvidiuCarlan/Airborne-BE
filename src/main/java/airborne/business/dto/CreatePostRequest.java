package airborne.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    private String content;
    private String image;
}
