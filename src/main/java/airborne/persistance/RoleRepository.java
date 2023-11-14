package airborne.persistance;

import airborne.persistance.entity.RoleEnum;
import airborne.persistance.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(RoleEnum roleEnum);
}
