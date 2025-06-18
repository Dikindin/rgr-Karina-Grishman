package worktimemanagement.service.impl;

import worktimemanagement.entity.WorkLog;
import worktimemanagement.repository.WorkLogRepository;
import worktimemanagement.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkLogServiceImpl implements WorkLogService {

    @Autowired
    private WorkLogRepository workLogRepository;

    @Override
    public WorkLog read(Long id) {
        return workLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<WorkLog> read() {
        return workLogRepository.findAll();
    }

    @Override
    public void save(WorkLog entity) {
        workLogRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        workLogRepository.deleteById(id);
    }

    @Override
    public List<WorkLog> getWorkLogsByTask(Long taskId) {
        return workLogRepository.findByTaskId(taskId);
    }
}
