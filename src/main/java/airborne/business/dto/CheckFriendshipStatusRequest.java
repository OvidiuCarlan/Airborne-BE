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
public class CheckFriendshipStatusRequest {
    @NotNull
    private Long loggedInUser;
    @NotNull
    private Long otherUser;
}
