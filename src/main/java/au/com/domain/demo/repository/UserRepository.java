package au.com.domain.demo.repository;

import au.com.domain.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mseskander .
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
