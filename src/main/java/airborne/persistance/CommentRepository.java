package airborne.persistance;

import airborne.persistance.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c FROM CommentEntity c WHERE c.postId = :postId ORDER BY c.id DESC")
    List<CommentEntity> findAllByPostId(@Param("postId") long postId);
}
