package airborne.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    private Long id;
    private String name;
    private String email;
    private String password;
}
