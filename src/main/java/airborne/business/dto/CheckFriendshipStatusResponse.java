package airborne.business.dto;

import airborne.persistance.entity.FriendshipEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckFriendshipStatusResponse {
    private Long id;
    private FriendshipEnum status;
}
