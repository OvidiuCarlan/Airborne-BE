package airborne.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "user_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NonNull
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
