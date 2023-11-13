package airborne.persistance;

import airborne.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

   // UserEntity save(UserEntity user);
    //void deleteById(long userId);
    //List<UserEntity> getAll();
    //Optional<UserEntity> findById(long userId);

}
