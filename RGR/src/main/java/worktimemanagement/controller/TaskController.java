package worktimemanagement.controller;

import worktimemanagement.entity.Task;
import worktimemanagement.service.TaskService;
import worktimemanagement.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Получение списка всех задач
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.read();
        return ResponseEntity.ok(tasks);
    }

    // Получение конкретной задачи по id
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.read(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(task);
    }

    // Создание новой задачи (требуется роль ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createTask(@Validated(ValidationGroups.OnCreate.class) @RequestBody Task task) {
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Обновление задачи по id (требуется роль ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(
            @PathVariable Long id,
            @Validated(ValidationGroups.OnUpdate.class) @RequestBody Task task) {
        task.setId(id);
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Удаление задачи по id (требуется роль ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok("Task deleted successfully.");
    }
}
