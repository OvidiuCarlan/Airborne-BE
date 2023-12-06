package airborne.domain;

import airborne.persistance.entity.FriendshipEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {
    private Long id;
    private Long senderId;
    private Long recipientId;
    private FriendshipEnum status;
}
