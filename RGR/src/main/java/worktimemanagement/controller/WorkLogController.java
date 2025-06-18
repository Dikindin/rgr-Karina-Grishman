package worktimemanagement.controller;

import worktimemanagement.entity.WorkLog;
import worktimemanagement.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/worklogs")
public class WorkLogController extends AbstractController<WorkLog> {

    @Autowired
    private WorkLogService workLogService;

    // GET: Получение всех записей (наследуется из AbstractController)
    @Override
    @GetMapping
    public ResponseEntity<List<WorkLog>> get() {
        return super.get();
    }

    // GET: Получение конкретной записи по ID (наследуется из AbstractController)
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<WorkLog> getById(@PathVariable long id) {
        return super.getById(id);
    }

    // POST: Создание новой записи учёта времени (переопределяем унаследованный метод)
    @Override
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<String> post(@Valid @RequestBody WorkLog workLog) {
        workLogService.save(workLog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PUT: Обновление записи по ID (переопределяем унаследованный метод с уникальным URI)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorkLog(@PathVariable long id,
                                                @Valid @RequestBody WorkLog workLog) {
        workLog.setId(id);
        workLogService.save(workLog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DELETE: Удаление записи по ID (переопределяем унаследованный метод)
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        workLogService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public WorkLogService getService() {
        return workLogService;
    }
}
