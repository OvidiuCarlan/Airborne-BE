package airborne.business.dto;

import airborne.persistance.entity.UserRoleEntity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private UserRoleEntity role;
}
