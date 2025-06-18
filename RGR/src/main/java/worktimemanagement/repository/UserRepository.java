package worktimemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import worktimemanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
