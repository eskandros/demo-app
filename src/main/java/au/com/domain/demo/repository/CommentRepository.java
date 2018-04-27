package au.com.domain.demo.repository;

import au.com.domain.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mseskander .
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
