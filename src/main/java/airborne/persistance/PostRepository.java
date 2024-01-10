package airborne.persistance;

import airborne.domain.UserPostCount;
import airborne.persistance.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.userId = ?1")
    List<PostEntity> getPostEntityByUserId(long id);

    @Query("SELECT p FROM PostEntity p WHERE p.userId = :userId")
    Page<PostEntity> getUserPostsPaginated(@Param("userId") long userId, Pageable pageable);
    @Query("SELECT p FROM PostEntity p " +
            "WHERE p.userId IN " +
            "(SELECT DISTINCT CASE " +
            "                  WHEN f.senderId = :userId THEN f.recipientId " +
            "                  ELSE f.senderId " +
            "               END " +
            "FROM FriendshipEntity f " +
            "WHERE f.senderId = :userId OR f.recipientId = :userId) " +
            "AND p.userId <> :userId")
    Page<PostEntity> getFeedPosts(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new airborne.domain.UserPostCount(u.id, u.name, COUNT(p)) " +
            "FROM PostEntity p JOIN UserEntity u ON p.userId = u.id GROUP BY u.id, u.name")
    List<UserPostCount> getUserPostCounts();
}
