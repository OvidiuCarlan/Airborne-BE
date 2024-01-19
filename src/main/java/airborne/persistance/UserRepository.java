package airborne.persistance;

import airborne.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity getUserEntityByEmail(String username);

    @Query("SELECT u FROM UserEntity u WHERE :name is not null AND u.name like %:name%")
    List<UserEntity> filteredSearch(String name);

    @Query("SELECT u FROM UserEntity u " +
            "JOIN FriendshipEntity f ON u.id = f.recipientId OR u.id = f.senderId " +
            "WHERE ((f.senderId = :providedUserId OR f.recipientId = :providedUserId) " +
            "AND f.status = 'ACCEPTED') " +
            "AND u.id <> :providedUserId")
    List<UserEntity> getFriendList(@Param("providedUserId") Long providedUserId);
}
