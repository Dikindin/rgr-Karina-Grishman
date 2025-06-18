package worktimemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import worktimemanagement.dto.TagTaskCountDto;
import worktimemanagement.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT new worktimemanagement.dto.TagTaskCountDto(t.name, COUNT(ts)) " +
            "FROM Tag t LEFT JOIN t.tasks ts GROUP BY t.name")
    List<TagTaskCountDto> countTasksByTag();
}
