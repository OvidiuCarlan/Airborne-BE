package airborne.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserPostCount {
    private Long userId;
    private String username;
    private Long postCount;

    public UserPostCount(Long userId, String username, Long postCount){
        this.userId = userId;
        this.username = username;
        this.postCount = postCount;
    }
}
