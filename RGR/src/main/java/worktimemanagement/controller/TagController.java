package worktimemanagement.controller;

import worktimemanagement.dto.TagTaskCountDto;
import worktimemanagement.entity.Tag;
import worktimemanagement.service.TagService;
import worktimemanagement.validation.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // 3.1. Получить список всех меток
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.read();
        if (tags == null || tags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(tags);
    }

    // Новый эндпоинт: получить статистику по количеству задач для каждой метки
    @GetMapping("/stats")
    public ResponseEntity<List<TagTaskCountDto>> getTaskCountPerTag() {
        List<TagTaskCountDto> stats = tagService.getTaskCountsPerTag();
        if (stats == null || stats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(stats);
    }

    // 3.2. Создать новую метку (только для админа)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createTag(@Validated(ValidationGroups.OnCreate.class) @RequestBody Tag tag) {
        tagService.save(tag);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 3.3. Обновить метку (только для админа)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTag(@PathVariable Long id,
                                            @Validated(ValidationGroups.OnUpdate.class) @RequestBody Tag tag) {
        tag.setId(id);
        tagService.save(tag);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 3.4. Удалить метку (только для админа)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id) {
        if (tagService.read(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        tagService.delete(id);
        return ResponseEntity.ok("Tag deleted successfully.");
    }
}
