package worktimemanagement.service;

import worktimemanagement.entity.Task;
import java.util.List;

public interface TaskService {
    void save(Task task);
    Task read(Long id);
    List<Task> read();
    void delete(Long id);
}
