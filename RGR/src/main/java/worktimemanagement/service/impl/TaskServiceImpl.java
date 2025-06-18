package worktimemanagement.service.impl;

import worktimemanagement.entity.Task;
import worktimemanagement.entity.Tag;
import worktimemanagement.repository.TaskRepository;
import worktimemanagement.repository.TagRepository;
import worktimemanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void save(Task task) {
        // Если были переданы теги, то подставляем управляемые сущности
        if (task.getTags() != null && !task.getTags().isEmpty()) {
            List<Tag> managedTags = new ArrayList<>();
            for (Tag tag : task.getTags()) {
                Tag managedTag = tagRepository.findById(tag.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Tag not found, id: " + tag.getId()));
                managedTags.add(managedTag);
            }
            task.setTags(managedTags);
        }
        taskRepository.save(task);
    }

    @Override
    public Task read(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> read() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
