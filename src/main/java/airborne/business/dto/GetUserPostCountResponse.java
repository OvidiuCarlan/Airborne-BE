package airborne.business.dto;

import airborne.domain.UserPostCount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPostCountResponse {
    private List<UserPostCount> userPostCounts;
}
