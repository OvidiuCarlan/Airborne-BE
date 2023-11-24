package airborne.persistance;

import airborne.persistance.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
