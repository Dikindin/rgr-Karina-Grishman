package worktimemanagement.service;

import worktimemanagement.entity.WorkLog;
import java.util.List;

public interface WorkLogService extends Service<WorkLog> {
    List<WorkLog> getWorkLogsByTask(Long taskId);
}
