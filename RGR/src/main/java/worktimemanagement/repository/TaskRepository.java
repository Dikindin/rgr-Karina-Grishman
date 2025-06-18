package worktimemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import worktimemanagement.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Дополнительных методов здесь не требуется
}
