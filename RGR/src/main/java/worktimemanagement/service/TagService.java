package worktimemanagement.service;

import worktimemanagement.dto.TagTaskCountDto;
import worktimemanagement.entity.Tag;

import java.util.List;

public interface TagService {

    void save(Tag tag);

    Tag read(Long id);

    List<Tag> read();

    void delete(Long id);

    // Новый метод для получения статистики: количество задач для каждой метки
    List<TagTaskCountDto> getTaskCountsPerTag();
}
