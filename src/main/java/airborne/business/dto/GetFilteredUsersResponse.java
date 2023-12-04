package airborne.business.dto;

import airborne.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFilteredUsersResponse {
    private List<User> filteredSearchUsers;
}
