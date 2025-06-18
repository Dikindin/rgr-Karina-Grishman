package worktimemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import worktimemanagement.validation.ValidationGroups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity {

    @NotBlank(message = "Описание не может быть пустым", groups = ValidationGroups.OnCreate.class)
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Дата выдачи обязательна", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "assigned_date", nullable = false)
    private LocalDateTime assignedDate;

    @NotNull(message = "Дата дедлайна обязательна", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @NotBlank(message = "Статус не может быть пустым", groups = {ValidationGroups.OnCreate.class, ValidationGroups.OnUpdate.class})
    @Column(nullable = false)
    private String status;

    // Чтобы избежать циклического вывода – при сериализации тегов игнорируем обратное поле tasks
    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnoreProperties("tasks")
    private List<Tag> tags = new ArrayList<>();

    // Геттеры и сеттеры

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }
    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
