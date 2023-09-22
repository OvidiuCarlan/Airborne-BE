package airborne.persistance;

import airborne.persistance.entity.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    UserEntity save(UserEntity user);
    void deleteById(long userId);
    List<UserEntity> getAll();
    Optional<UserEntity> findById(long userId);

}
