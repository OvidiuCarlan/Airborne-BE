package airborne.persistance;

import airborne.domain.Post;
import airborne.persistance.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p WHERE p.userId = ?1")
    List<PostEntity> getPostEntityByUserId(long id);

    @Query("SELECT p FROM PostEntity p WHERE p.userId = :userId")
    Page<PostEntity> getUserPostsPaginated(
            @Param("userId") long userId,
            Pageable pageable
    );


}
