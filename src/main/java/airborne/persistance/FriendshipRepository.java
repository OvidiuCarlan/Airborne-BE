package airborne.persistance;

import airborne.persistance.entity.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {
    @Query("SELECT f FROM FriendshipEntity f WHERE f.id = ?1")
    FriendshipEntity getPostEntityById(Long id);

    @Query("SELECT f FROM FriendshipEntity f WHERE f.senderId = :senderId AND f.recipientId = :recipientId")
    FriendshipEntity getFriendshipBySenderIdAndRecipientId(Long senderId, Long recipientId);

}
